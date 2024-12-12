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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.logan.compose.demo.R
import org.logan.compose.demo.base.fragment.BaseFragment

/**
 * desc: 第2章 常用基本组件 - 文本 <br/>
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
        Spacer(modifier = Modifier.height(8.dp))

        TextMaxLinesDemoView()
        Spacer(modifier = Modifier.height(8.dp))

        TextFontFamilyDemoView()
        Spacer(modifier = Modifier.height(8.dp))

        TextAnnotatedStringDemoView()
        Spacer(modifier = Modifier.height(8.dp))
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
        text = "Hello Wolrd！正在使用 Jetpack Compose 构建 Android 界面！超长文本案例",
        style = MaterialTheme.typography.bodyLarge
    )
    Text(
        text = "Hello Wolrd！正在使用 Jetpack Compose 构建 Android 界面！超长文本案例",
        style = MaterialTheme.typography.bodyLarge,
        maxLines = 1
    )
    Text(
        text = "Hello Wolrd！正在使用 Jetpack Compose 构建 Android 界面！超长文本案例",
        style = MaterialTheme.typography.bodyLarge,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun TextFontFamilyDemoView() {
    Text("FontFamily.Monospace", fontFamily = FontFamily.Monospace)
    Text("FontFamily.Cursive", fontFamily = FontFamily.Cursive)
    Text("FontFamily.SansSerif", fontFamily = FontFamily.SansSerif)
    Text("FontFamily.Serif", fontFamily = FontFamily.Serif)
    Text("res/font/alibaba_heavy", fontFamily = FontFamily(Font(R.font.alibaba_heavy)))
}

@Composable
fun TextAnnotatedStringDemoView() {
    // AnnotatedString, 是一个可组合的文本对象，它可以在文本中应用样式和格式，使用 SpanStyle、ParagraphStyle 等对象来处理样式和格式
    Text(text = buildAnnotatedString {
        // 使用 withStyle 方法来应用样式。参数 SpanStyle 可以处理文字样式，如：颜色、字体、大小、粗细、背景等
        withStyle(
            style = SpanStyle(
                fontSize = 18.sp, background = Color.Yellow, shadow = Shadow(
                    color = Color.Blue, offset = Offset.Zero, blurRadius = 25f
                )
            )
        ) {
            // 使用 append 添加显示文案
            append("你现在看的章节是")
        }

        withStyle(
            style = SpanStyle(fontSize = 24.sp, fontWeight = FontWeight.W900, color = Color.Red)
        ) {
            append("Text")
        }

        // 参数 ParagraphStyle 可以处理段落样式，设置文字的行高、对齐方式、缩进等。
        withStyle(
            style = ParagraphStyle(
                // 对齐方式
                textAlign = TextAlign.Start,
                // 行高
                lineHeight = 25.sp,
                // 缩进。firstLine 表示第一行的缩进，restLine 表示其他行的缩进。
                textIndent = TextIndent(firstLine = 25.sp, restLine = 5.sp)
            )
        ) {
            append("在刚刚讲过的内容中，我们学习了如何应用文字样式，以及如何限制文本的行数和处理溢出的视觉效果。\n")
            // 嵌套 withStyle
            withStyle(
                style = SpanStyle(fontWeight = FontWeight.W900, color = Color(0xFF59A869))
            ) {
                append("AnnotatedString")
            }
            append("是Compose处理文案技术，替代传统View SpanString。")
        }
    })
}

@Preview(showBackground = true)
@Composable
fun C2_2_BasicUIFragmentDemoViewPreview() {
    C2_2_BasicUIFragmentDemoView()
}


