package org.logan.compose.demo.ui.book.c3

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.logan.compose.demo.base.fragment.BaseFragment
import org.logan.compose.demo.ui.theme.AndroidComposeDemoTheme
import org.logan.compose.demo.ui.theme.Teal200

/**
 * desc: 主题 <br/>
 * time: 2024/12/27 13:59 <br/>
 * author: Logan <br/>
 * since: V 1.0 <br/>
 */
class C3_2_ThemeFragment : BaseFragment() {

    @Composable
    override fun MyFragmentView() {
        C3_2_ThemeFragmentSamplePreview()
    }

}

@Preview(showBackground = true)
@Composable
fun C3_2_ThemeFragmentSamplePreview() {
    AndroidComposeDemoTheme {
        C3_2_ThemeFragmentSample()
    }
}

// @OptIn(ExperimentalMaterial3Api::class)
@Composable
fun C3_2_ThemeFragmentSample() {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
    ) {
        SampleTextSample()
        Spacer(modifier = Modifier.height(12.dp))

        CompositionLocalSample()
        Spacer(modifier = Modifier.height(12.dp))

        StaticOrNotCompositionLocalDiffSample2()
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun SampleTextSample() {
    Text(
        text = "Hello World", color = MaterialTheme.colorScheme.primary
    )
}

@Composable
fun CompositionLocalSample() {
    // Compose 提供了两种创建 CompositionLocal 实例方式，分别是 CompositionLocalOf{ } 与 staticCompositionLocalOf{} 。
    // 在 Composable 树中的任何位置，都可以使用 CompositionLocalProvider() 为 CompositionLocal 设置新值。并通过 xxx.current 使用该值。
    // 子组件还可以覆盖父组件所传递下来的数值。

    val LocalString = compositionLocalOf { "Hello Animal" }

    Column(
        modifier = Modifier
            .height(100.dp)
            .width(150.dp)
            .border((0.2).dp, Color.Gray, RoundedCornerShape(6.dp))
            .padding(5.dp)
    ) {

        // 第1次修改 LocalString 值
        CompositionLocalProvider(LocalString provides "Hello Dog") {
            // 显示1
            Text(text = LocalString.current, color = Color.Green)

            // 第2次修改 LocalString 值
            CompositionLocalProvider(LocalString provides "Hello Cat") {
                // 显示2
                Text(text = LocalString.current, color = Color.Blue)
            }

        }

        // 显示3
        Text(text = LocalString.current, color = Color.Red)

        // 通过上面3次不同时机的显示 LocalString，但是值并不相同
    }
}

// compositionLocalOf 创建方式，当状态发生变化时，所有读取这个 CompositionLocal内部 current数值的组件都会发生重组(精确重组)。
// staticCompositionLocalOf，当状态发生变化时，CompositionLocalProvider 内部的所有组件都会发生重组。而不仅仅是读取其内部 current 数值的组件
// 精确重组是有一定代价的，compositionLocalOf 创建时候记录使用内部current的所有组件。
// 所以，官方建议，变化很小或者永不变化，建议使用 staticCompositionLocalOf 提高性能。

var isStatic = true
var recomposeFlag = "Init"
val compositionLocalName =
    if (isStatic) "StaticCompositionLocal场景" else "DynamicCompositionLocal场景"
var currentLocalColor = if (isStatic) staticCompositionLocalOf { Teal200 }
else compositionLocalOf { Teal200 }

@Composable
fun StaticOrNotCompositionLocalDiffSample2() {
    var color by remember { mutableStateOf(Color.Green) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "通过修改常量 isStatic=true|false, 观测 StaticCompositionLocal 与 DynamicCompositionLocal 区别，修改后重新运行程序才有效果",
                fontSize = 10.sp,
                color = Color.Red,
                lineHeight = 16.sp
            )
            Text(text = compositionLocalName, fontSize = 10.sp)
            Spacer(modifier = Modifier.height(5.dp))

            CompositionLocalProvider(
                currentLocalColor provides color
            ) {
                TaggedBox("Wrapper:${recomposeFlag}", 170.dp, Color.Red) {
                    TaggedBox("Middle:${recomposeFlag}", 140.dp, currentLocalColor.current) {
                        TaggedBox("Inner:${recomposeFlag}", 120.dp, Color.Yellow)
                    }
                }
            }

            Spacer(Modifier.height(5.dp))
            Button(onClick = {
                color = Color.Blue
            }) {
                Text(text = "Change Theme")
            }
        }
    }

    recomposeFlag = "Recompose"
}

@Composable
fun TaggedBox(tag: String, size: Dp, background: Color, content: @Composable () -> Unit = {}) {
    Column(
        modifier = Modifier
            .size(size)
            .background(background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = tag, fontSize = 11.sp
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}
