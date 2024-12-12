package org.logan.compose.demo.ui.book.c2

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.logan.compose.demo.R
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
        C2_2_BasicUIFragmentDemoView()
    }

}

@Composable
fun C2_2_BasicUIFragmentDemoView(modifier: Modifier = Modifier) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        TextDemoView()
        Spacer(modifier = Modifier.height(5.dp))

        TextMaxLinesDemoView()
    }
}

@Composable
fun TextDemoView() {
    // 指定字符串
    Text(text = "Hello World!")

    Text(
        // 从资源中加载字符串, 除了 stringResource，compose 也提供了 colorResource、painterResource(Drawable类型资源)等方法加载资源
        text = stringResource(R.string.hello_world),
        modifier = Modifier
            .background(color = Color.Black)
            .fillMaxWidth()
            .padding(end = 15.dp),
        color = Color.White, // 文字颜色
        fontSize = 20.sp, // 文字大小
        fontStyle = FontStyle.Italic, // 文字样式,  FontStyle.Normal
        fontWeight = FontWeight.Bold, // 文字粗细， FontWeight.Light、FontWeight.Thin、FontWeight.W100
        fontFamily = FontFamily(Font(R.font.alibaba_heavy)), // 文本字体，使用默认的 FontFamily.Monospace
        letterSpacing = 2.5.sp, // 文本之间间距 TextUnit.Unspecified
        textDecoration = TextDecoration.Underline, // 文本的装饰，下划线、删除线等。TextDecoration.LineThrough, TextDecoration.None
        textAlign = TextAlign.End, // 文本的对齐方式
        lineHeight = 50.sp, // 文本行高
        overflow = TextOverflow.Ellipsis, // 文本溢出时视觉效果，省略号、裁剪等，配合 maxLines 属性一起使用
        softWrap = false, // 文本是否支持自动换行, 默认true.
        maxLines = 1, // 文本的最大行数
        // 文本布局完成后(文本发生变化之后? TODO Logan 待测试) 的回调 ， textLayoutResult， 包含文本的布局信息，如文本的宽度、高度、行高等
        onTextLayout = { textLayoutResult ->
            Log.v(
                "TLog",
                "Text width: ${textLayoutResult.size.width}, lineCount:${textLayoutResult.lineCount}"
            )
        },
        // 文本 style 配置，如颜色、字体、行高、间距、文本粗细等。
        // 注意：如果同时设置了 style 和 Text 的 color、fontSize 等属性，那么 style 中同名配置会被 Text 属性覆盖掉。
        // 可通过 LocalTextStyle.current 来获取当前的文本样式，然后通过 copy() 进行修改, 并将修改后的样式传递给 Text 组件。
        style = LocalTextStyle.current.copy(
            // 这些属性不起作用，因为上面属性已经设置，会覆盖下面的配置。
            color = Color.Red, fontSize = 40.sp, textAlign = TextAlign.Center
        )
    )

    Text(
        text = "Hello World, Goodbye World!",
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.LineThrough,
        // 使用 Material3 内设的文本样式: titleMedium、titleSmall、bodyLarge、bodyMedium、bodySmall 等
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
fun TextMaxLinesDemoView() {
    Text(
        text = "Hello Wolrd！正在使用 Jetpack Compose 构建 Android 界面！",
        style = MaterialTheme.typography.bodyLarge
    )
    Text(
        text = "Hello Wolrd！正在使用 Jetpack Compose 构建 Android 界面！",
        style = MaterialTheme.typography.bodyLarge,
        maxLines = 1
    )
    Text(
        text = "Hello Wolrd！正在使用 Jetpack Compose 构建 Android 界面！",
        style = MaterialTheme.typography.bodyLarge,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Preview(showBackground = true)
@Composable
fun C2_2_BasicUIFragmentDemoViewPreview() {
    C2_2_BasicUIFragmentDemoView()
}


