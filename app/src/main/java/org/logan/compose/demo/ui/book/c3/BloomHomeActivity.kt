package org.logan.compose.demo.ui.book.c3

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import org.logan.compose.demo.base.activity.BaseActivity
import org.logan.compose.demo.ui.theme.AndroidComposeDemoTheme

/**
 * desc: Bloom - 主页 <br/>
 * time: 2024/12/23 18:53 <br/>
 * author: Logan <br/>
 * since: V 1.0 <br/>
 */
class BloomHomeActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidComposeDemoTheme {
                Text("home")
            }
        }
    }

}