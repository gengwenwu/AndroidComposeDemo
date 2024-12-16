package org.logan.compose.demo.ui.book

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
import org.logan.compose.demo.ui.book.c2.C2_1_ComposeModifierPropertyFragment
import org.logan.compose.demo.ui.book.c2.C2_2_ComposeTextFragment
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

    private fun showClickMenu(fragment: Fragment) {
        supportFragmentManager.beginTransaction().run {
            supportFragmentManager.findFragmentById(R.id.fragment_container)?.let {
                this.hide(it)
            }

            this.add(R.id.fragment_container, fragment, null)
            this.commit()
            this.addToBackStack(null) // 将事务添加到回退栈
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

private fun getMenuConfigs(): Map<String, Fragment?> {
    return mapOf(
        "2.1 Modifier组件属性" to C2_1_ComposeModifierPropertyFragment(),
        "2.2 文本组件" to C2_2_ComposeTextFragment(),
        // "C4 状态管理与重组" to null,
        // "C5 动画" to null,
        // "C6 手势处理" to null,
        // "C7 页面导航" to null,
        // "C8 TODO" to null
    )
}

@Preview(showBackground = true)
@Composable
fun ActivityPreview() {
    AndroidComposeDemoTheme {
        FragmentContainer(fragment = NavMenuFragment(getMenuConfigs()))
    }
}
