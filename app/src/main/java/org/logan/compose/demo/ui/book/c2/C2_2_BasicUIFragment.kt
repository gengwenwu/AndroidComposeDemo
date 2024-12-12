package org.logan.compose.demo.ui.book.c2

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
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
        fontStyle = FontStyle.Italic, // 文字样式
        fontWeight = FontWeight.Bold, // 文字粗细
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
        // 文本的风格配置，如颜色、字体、行高等。
        // 可通过 LocalTextStyle.current 来获取当前的文本样式，然后通过 copy() 进行修改, 并将修改后的样式传递给 Text 组件
        style = LocalTextStyle.current.copy(
            textAlign = TextAlign.Center, fontSize = 25.sp, color = Color.Red
        )
    )

}

@Preview(showBackground = true)
@Composable
fun C2_2_BasicUIFragmentDemoViewPreview() {
    C2_2_BasicUIFragmentDemoView()
}


