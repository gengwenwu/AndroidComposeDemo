package org.logan.compose.demo.ui.book.c2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.logan.compose.demo.base.fragment.BaseFragment

/**
 * desc: 约束布局案例 <br/>
 * time: 2024/12/18 17:24 <br/>
 * author: Logan <br/>
 * since: V 1.0 <br/>
 */
class C2_3_4_ConstraintLayoutFragment : BaseFragment() {

    @Composable
    override fun MyFragmentView() {
        C2_3_4_ConstraintLayoutSample()
    }

}

@Preview
@Composable
fun C2_3_4_ConstraintLayoutSamplePreview() {
    C2_3_4_ConstraintLayoutSample()
}

@Composable
fun C2_3_4_ConstraintLayoutSample() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

    }
}


