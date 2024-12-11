package org.logan.compose.demo.ui.book.c2

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.logan.compose.demo.base.fragment.BaseFragment

/**
 * desc: 第2章 常用基本组件 <br/>
 * time: 2024/12/10 14:46 <br/>
 * author: Logan <br/>
 * since: V 1.0 <br/>
 */
class C2_2_BasicUIFragment : BaseFragment() {

    @Composable
    override fun MyFragmentView() {
        Column() {
            Text("常用基本组件")
        }
    }

}