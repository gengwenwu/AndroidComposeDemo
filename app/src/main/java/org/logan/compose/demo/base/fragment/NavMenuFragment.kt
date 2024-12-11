package org.logan.compose.demo.base.fragment

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import org.logan.compose.demo.utils.extension.showMsg

/**
 * desc: 导航菜单 <br/>
 * time: 2024/12/10 18:05 <br/>
 * author: Logan <br/>
 * since: V 1.0 <br/>
 */
class NavMenuFragment(
    private val menu: Map<String, Fragment?>,
    private val menuClickConsumer: ((Fragment) -> Unit)? = null
) : BaseFragment() {


    @Composable
    override fun MyFragmentView() {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            menu.forEach {
                item {
                    MenuButton(it.key, it.value, menuClickConsumer)
                }
            }
        }
    }
}

@Composable
fun MenuButton(text: String, fragment: Fragment?, menuClickConsumer: ((Fragment) -> Unit)? = null) {
    val context = LocalContext.current

    Button(
        //
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .height(35.dp),
        //
        onClick = {
            if (fragment == null) {
                context.showMsg("该功能未实现")
            } else {
                menuClickConsumer?.invoke(fragment)
            }
        }) {
        Text(text = text)
    }
}
