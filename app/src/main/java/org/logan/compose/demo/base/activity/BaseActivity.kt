package org.logan.compose.demo.base.activity

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.util.Consumer
import androidx.fragment.app.FragmentActivity

/**
 * desc: activity 基类 <br/>
 * time: 2024/12/9 18:24 <br/>
 * author: Logan <br/>
 * since: V 1.0 <br/>
 */
open class BaseActivity : FragmentActivity() {


//    fun showMsg(msg: String?) {
//        msg?.let {
//            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
//        }
//    }

}

fun goNextPage(
    activity: Activity, desActivityCls: Class<*>, intentConsumer: Consumer<Intent>? = null
) {
    activity.startActivity(Intent(activity, desActivityCls).apply {
        intentConsumer?.accept(this)
    })
}

@Composable
fun FillTopBarModifier(innerPadding: PaddingValues) = Modifier
    .padding(innerPadding)
    .padding(
        top = WindowInsets.statusBars
            .asPaddingValues()
            .calculateTopPadding()
    )