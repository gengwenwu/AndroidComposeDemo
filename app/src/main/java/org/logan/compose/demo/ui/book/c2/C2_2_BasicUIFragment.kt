package org.logan.compose.demo.ui.book.c2

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import org.logan.compose.demo.utils.extension.showMsg

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
    // val scrollState = rememberScrollState()
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
        //.scrollable(state = scrollState, orientation = Orientation.Vertical)
    ) {
        item {
            TextDemoView()
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            TextMaxLinesDemoView()
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            TextFontFamilyDemoView()
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            TextAnnotatedStringDemoView()
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            TextClickableDemoView()
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            SelectionContainerDemoView()
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            TextFieldDemoView()
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            OutlinedTextFieldDemoView()
            Spacer(modifier = Modifier.height(102.dp))
        }
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
        // 文本 style 配置(TextStyle)，如: 颜色、字体、行高、间距、文本粗细等。
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
        text = "正在使用 Jetpack Compose 构建 Android 界面！超长文本案例",
        style = MaterialTheme.typography.bodyLarge
    )
    Text(
        text = "正在使用 Jetpack Compose 构建 Android 界面！超长文本案例",
        style = MaterialTheme.typography.bodyLarge,
        maxLines = 1
    )
    Text(
        text = "正在使用 Jetpack Compose 构建 Android 界面！超长文本案例",
        style = MaterialTheme.typography.bodyLarge,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun TextFontFamilyDemoView() {
    // 使用字体案例
    Text("FontFamily.Monospace", fontFamily = FontFamily.Monospace)
    Text("FontFamily.Cursive", fontFamily = FontFamily.Cursive)
    Text("FontFamily.SansSerif", fontFamily = FontFamily.SansSerif)
    Text("FontFamily.Serif", fontFamily = FontFamily.Serif)
    Text("res/font/alibaba_heavy", fontFamily = FontFamily(Font(R.font.alibaba_heavy)))
}

@Composable
fun TextAnnotatedStringDemoView() {
    // AnnotatedString, 是一个可组合的文本对象，它可以在文本中应用样式和格式，使用 SpanStyle、ParagraphStyle 等对象来处理样式和格式
    Text(
        text = buildAnnotatedString {
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
                style = SpanStyle(fontSize = 25.sp, fontWeight = FontWeight.W900, color = Color.Red)
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
        }, style = LocalTextStyle.current.copy(
            // SpanStyle 或 ParagraphStyle 中的优先级高于 TextStyle 中的同名属性配置。
            // 下面 fontSize = 12.sp，在上面部分已经设置了 fontSize 后，就无效了 ，如：“你现在看的章节是Text” 文字的大小
            fontSize = 12.sp
        )
    )
}

@Composable
fun TextClickableDemoView() {
    val clickTextTag = "URL"

    val annotationText = buildAnnotatedString {
        withStyle(
            style = ParagraphStyle(lineHeight = 25.sp)
        ) {
            append("Compose Text官方案例")

            // 通过 pushStringAnnotation 方法，给文本添加一个标签，用于后续点击处理逻辑。
            // 为 pushStringAnnotation 与 pop 之间的区域，添加标签，后续查询到该标签的文本。
            // 设置处理事件。如：打开链接、拨打号码，等
            pushStringAnnotation(
                tag = clickTextTag,
                annotation = "https://developer.android.google.cn/develop/ui/compose/text?hl=zh-cn"
            )
            withStyle(
                style = SpanStyle(
                    color = Color.Blue,
                    fontStyle = FontStyle.Italic,
                    textDecoration = TextDecoration.Underline
                )
            ) {
                append("点击我")
            }

            // 使用 pop 方法，将标签从文本中移除
            pop()

            append("查看详情")
        }
    }

    val context = LocalContext.current

    // 可点击文本组件 ClickableText
    ClickableText(
        // 显示文案
        text = annotationText,
        // 点击事件
        onClick = { offset ->
            // 获取被点击区域的标签为 "URL" 的 Annotation 并进行处理
            annotationText.getStringAnnotations(tag = clickTextTag, start = offset, end = offset)
                .firstOrNull()?.let {
                    context.showMsg("跳转到：${it.item}")
                }
        })
}


@Composable
fun SelectionContainerDemoView() {
    SelectionContainer {
        // Text 组件，默认是不支持复制的，需要使用 SelectionContainer 组件包裹起来，才能支持复制
        Text(
            text = "这是一个长按可以被复制的文字",
            color = Color.Gray,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun TextFieldDemoView() {
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    TextField(value = userName, onValueChange = {
        // 输入变更，通知 view 刷新
        userName = it
    }, label = {  // 输入框的标签
        Text(text = "用户名")
    }, placeholder = { // 输入框的占位符
        Text(text = "请输入用户名")
    }, leadingIcon = { // 输入框前图标
        // icon 图标 (使用系统)
        Icon(imageVector = Icons.Filled.AccountBox, contentDescription = null)
    })

    TextField(value = password, onValueChange = {
        password = it
    }, label = {
        Text(text = "密码")
    }, placeholder = {
        Text(text = "请输入密码")
    }, leadingIcon = {  // 输入框前图标
        Icon( // 使用自定义
            painter = painterResource(R.drawable.ic_login_lock),
            contentDescription = null,
            modifier = Modifier.size(25.dp)
        )
    }, trailingIcon = {  // 输入框后图标，既：X按钮
        IconButton(onClick = {
            password = "" // 清空密码
        }) {
            Icon(imageVector = Icons.Filled.Clear, contentDescription = null)
        }
    }, maxLines = 1)

}


@Composable
fun OutlinedTextFieldDemoView() {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(value = text, onValueChange = {
        text = it
    }, label = {
        Text(text = "用户名")
    }, placeholder = {
        Text(text = "请输入用户名")
    })
}

@Preview(showBackground = true)
@Composable
fun C2_2_BasicUIFragmentDemoViewPreview() {
    C2_2_BasicUIFragmentDemoView()
}


