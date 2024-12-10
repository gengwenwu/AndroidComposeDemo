package org.logan.compose.demo.ui.book.c2

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.logan.compose.demo.base.fragment.BaseFragment

/**
 * desc: 第2章 第1个案例 <br/>
 * time: 2024/12/10 14:43 <br/>
 * author: Logan <br/>
 * since: V 1.0 <br/>
 */
class C2_1_TestFragment : BaseFragment() {

    @Composable
    override fun MyFragmentView() {
        Column() {
            Text("这是一个测试的fragment 1")
        }
    }

}