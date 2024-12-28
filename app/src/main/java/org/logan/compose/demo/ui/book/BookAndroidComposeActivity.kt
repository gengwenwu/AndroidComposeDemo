package org.logan.compose.demo.ui.book

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentContainerView
import org.logan.compose.demo.R
import org.logan.compose.demo.base.activity.BaseActivity
import org.logan.compose.demo.base.activity.FillTopBarModifier
import org.logan.compose.demo.base.fragment.NavMenuFragment
import org.logan.compose.demo.ui.book.c2.C2_1_ModifierPropertyFragment
import org.logan.compose.demo.ui.book.c2.C2_2_1_TextFragment
import org.logan.compose.demo.ui.book.c2.C2_2_2_ImageFragment
import org.logan.compose.demo.ui.book.c2.C2_2_3_ButtonFragment
import org.logan.compose.demo.ui.book.c2.C2_2_4_CheckboxFragment
import org.logan.compose.demo.ui.book.c2.C2_2_5_DialogFragment
import org.logan.compose.demo.ui.book.c2.C2_3_1_LineLayoutFragment
import org.logan.compose.demo.ui.book.c2.C2_3_4_ConstraintLayoutFragment
import org.logan.compose.demo.ui.book.c2.C2_3_5_ScaffoldFragment
import org.logan.compose.demo.ui.book.c2.C2_4_ColumnFragment
import org.logan.compose.demo.ui.book.c3.BloomWelcomeActivity
import org.logan.compose.demo.ui.book.c3.C3_2_ThemeFragment
import org.logan.compose.demo.ui.theme.AndroidComposeDemoTheme

/**
 * desc: 《Android Compose 从入门到实践案例》 <br/>
 * time: 2024/12/9 18:32 <br/>
 * author: Logan <br/>
 * since: V 1.0 <br/>
 */
class BookAndroidComposeActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AndroidComposeDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FragmentContainer(
                        FillTopBarModifier(innerPadding).fillMaxWidth(),
                        NavMenuFragment(getMenuConfigs(), ::showClickMenu)
                    )
                }
            }
        }
    }

    private fun showClickMenu(any: Any) {
        if (any is Fragment) {
            supportFragmentManager.beginTransaction().run {
                supportFragmentManager.findFragmentById(R.id.fragment_container)?.let {
                    this.hide(it)
                }

                this.add(R.id.fragment_container, any, null)
                this.commit()
                this.addToBackStack(null) // 将事务添加到回退栈
            }
        } else if (any is Class<*>) {
            this.startActivity(Intent(this, any))
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}

@Composable
fun FragmentContainer(modifier: Modifier = Modifier, fragment: Fragment) {
    val fragmentManager = (LocalContext.current as FragmentActivity).supportFragmentManager
    val fragmentTransaction = remember { fragmentManager.beginTransaction() }

    Column(modifier) {
        AndroidView(
            // factory
            factory = { context ->
                FragmentContainerView(context).apply {
                    this.id = R.id.fragment_container // View.generateViewId()
                    fragmentTransaction.add(this.id, fragment, null)
                    fragmentTransaction.commit()
                }
            }
//            ,
//            // update
//            update = { view ->
//                fragmentTransaction.run {
//                    if (fragmentManager.findFragmentById(view.id) == null) {
//                        add(view.id, fragment, null)
//                    } else {
//                        hide(fragmentManager.findFragmentById(view.id)!!)
//                        add(view.id, fragment, null)
//                    }
//                    commit()
//                }
//            }
        )
    }
}

private fun getMenuConfigs(): Map<String, Any?> {
    return mapOf(
        "2.1 Modifier属性" to C2_1_ModifierPropertyFragment(),
        "2.2.1 文本" to C2_2_1_TextFragment(),
        "2.2.2 图片" to C2_2_2_ImageFragment(),
        "2.2.3 Button" to C2_2_3_ButtonFragment(),
        "2.2.4 选择器" to C2_2_4_CheckboxFragment(),
        "2.2.5 对话框、进度条" to C2_2_5_DialogFragment(),
        "2.3.1 线性布局(线性、帧)" to C2_3_1_LineLayoutFragment(),
        "2.3.4 约束布局" to C2_3_4_ConstraintLayoutFragment(),
        "2.3.5 Scaffold" to C2_3_5_ScaffoldFragment(),
        "2.4 列" to C2_4_ColumnFragment(),
        "2.4 列" to C2_4_ColumnFragment(),
        "3.1 Bloom定制UI" to BloomWelcomeActivity::class.java,
        "3.2 主题" to C3_2_ThemeFragment(),
        // "C7 页面导航" to null,
    )
}

@Preview(showBackground = true)
@Composable
fun ActivityPreview() {
    AndroidComposeDemoTheme {
        FragmentContainer(fragment = NavMenuFragment(getMenuConfigs()))
    }
}
