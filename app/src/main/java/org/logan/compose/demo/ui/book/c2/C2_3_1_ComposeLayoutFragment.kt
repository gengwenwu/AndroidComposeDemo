package org.logan.compose.demo.ui.book.c2

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.logan.compose.demo.base.fragment.BaseFragment

/**
 * desc: 布局(线线布局、帧布局) <br/>
 * time: 2024/12/18 14:18 <br/>
 * author: Logan <br/>
 * since: V 1.0 <br/>
 */
class C2_3_1_ComposeLayoutFragment : BaseFragment() {

    @Composable
    override fun MyFragmentView() {
        C2_3_1_ComposeLayoutSample()
    }
}

@Preview(showBackground = true)
@Composable
fun C2_3_1_ComposeLayoutSamplePreview() {
    C2_3_1_ComposeLayoutSample()
}

@Composable
fun C2_3_1_ComposeLayoutSample() {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
    ) {
        ColumnSample()
        Spacer(Modifier.height(8.dp))

        ColumnSample2()
    }
}

@Composable
fun ColumnSample() {
    // Column 垂直方向布局，默认从上到下，从左到右。
    Column(modifier = Modifier.border(1.dp, Color.Red)) {
        Text("Hello, World.")
        Text("Jetpack Compose")
    }
}

@Composable
fun ColumnSample2() {
    Column(
        modifier = Modifier
            .border(1.dp, Color.Blue)
            .size(width = 200.dp, height = 80.dp),
        // 只有指定 Column 高宽后，verticalArrangement、horizontalAlignment 才会生效。
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Hello, World.")
        Text(
            "Jetpack Compose",
            // 使用 align 方法设置单个组件对其方式，该方式优先级要高于 Column 参数 horizontalAlignment。
            // 对于 Column 布局子项，Modifier.align 只能设置水平方向的位置，
            // 对于 Row 布局子项，Modifier.align 只能设置垂直方向的位置。
            modifier = Modifier.align(Alignment.End)
        )
    }
}
