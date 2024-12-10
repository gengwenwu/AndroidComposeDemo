package org.logan.compose.demo.utils.extension

import android.content.Context
import android.widget.Toast

/**
 * desc: view 扩展类 <br/>
 * time: 2024/12/10 19:08 <br/>
 * author: Logan <br/>
 * since: V 1.0 <br/>
 */

fun Context?.showMsg(msg: String) {
    this?.let {
        Toast.makeText(it, msg, Toast.LENGTH_SHORT).show()
    }
}