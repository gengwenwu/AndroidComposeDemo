package org.logan.compose.demo.ui.book.c2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.logan.compose.demo.base.fragment.BaseFragment

/**
 * desc: 第2章 常用基本组件 - 选择框 <br/>
 * time: 2024/12/17 18:54 <br/>
 * author: Logan <br/>
 * since: V 1.0 <br/>
 */
class C2_2_5_ComposeDialogFragment : BaseFragment() {

    @Composable
    override fun MyFragmentView() {
        C2_2_5_ComposeDialogSample()
    }

}

@Preview(showBackground = true)
@Composable
fun C2_2_5_ComposeDialogSamplePreview() {
    C2_2_5_ComposeDialogSample()
}

@Composable
fun C2_2_5_ComposeDialogSample(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
    ) {

        Spacer(Modifier.height(10.dp))

        DialogSample()
    }

}

@Composable
fun DialogSample() {
    val openDialog = remember { mutableStateOf(false) }

    Button(onClick = {
        openDialog.value = true
    }) {
        Text("打开Dialog")
    }

    // Compose Dialog 没有 show、dismiss 等方法，它是通过重组实现是否显示和关闭
    if (openDialog.value) {
        Dialog(
            // 关闭对话框执行的回调，在这里修改 openDialog.value 来关闭对话框
            onDismissRequest = {
                openDialog.value = false
            },
            // properties 定制对话框特性
            properties = DialogProperties(
                // 点击返回键是否关闭对话框
                dismissOnBackPress = true,
                // 点击对话框外部是否关闭对话框
                dismissOnClickOutside = true,
                // 是否使用平台默认宽度
                usePlatformDefaultWidth = true,
                // 对话框是否填充系统窗口
                decorFitsSystemWindows = false
            )
        ) {
            // content 自定义 Dialog 样式
            Box(
                modifier = Modifier
                    .size(200.dp, 50.dp)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "对话框内容", modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun AlertDialogSample() {

}