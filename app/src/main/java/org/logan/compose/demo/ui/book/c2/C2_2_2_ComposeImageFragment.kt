package org.logan.compose.demo.ui.book.c2

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.logan.compose.demo.base.fragment.BaseFragment

/**
 * desc: 第2章 常用基本组件 - 文本 <br/>
 * time: 2024/12/10 14:46 <br/>
 * author: Logan <br/>
 * since: V 1.0 <br/>
 */
class C2_2_2_ComposeImageFragment : BaseFragment() {

    @Composable
    override fun MyFragmentView() {
        C2_2_2_ComposeImageSample()
    }

}

@Preview(showBackground = true)
@Composable
fun C2_2_2_ComposeImageSamplePreview() {
    C2_2_2_ComposeImageSample()
}

@Composable
fun C2_2_2_ComposeImageSample(modifier: Modifier = Modifier) {
    // val scrollState = rememberScrollState()
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
        //.scrollable(state = scrollState, orientation = Orientation.Vertical)
    ) {

    }
}

//@Composable
//fun TextSample() {
//
//
//}



