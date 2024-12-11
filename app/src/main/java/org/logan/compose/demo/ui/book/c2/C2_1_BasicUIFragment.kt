package org.logan.compose.demo.ui.book.c2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.logan.compose.demo.R
import org.logan.compose.demo.base.fragment.BaseFragment

/**
 * desc: 第2章 常用UI <br/>
 * time: 2024/12/10 14:43 <br/>
 * author: Logan <br/>
 * since: V 1.0 <br/>
 */
class C2_1_BasicUIFragment : BaseFragment() {


    @Composable
    override fun MyFragmentView() {
        FragmentDemoView()
    }
}


@Composable
fun FragmentDemoView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            // 填充整个父布局(宽和高)
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        ModifierSizeDemo(modifier)
        Spacer(Modifier.height(5.dp))

        ModifierBackgroundDemo()
        Spacer(Modifier.height(5.dp))

        ModifierBorderPaddingDemo()
        Spacer(Modifier.height(5.dp))

        ModifierOffsetDemo()
        Spacer(Modifier.height(5.dp))

        ModifierMatchParentDemo()
        Spacer(Modifier.height(5.dp))

        ModifierWeightDemo()
        Spacer(Modifier.height(5.dp))
    }
}

@Composable
fun ModifierSizeDemo(modifier: Modifier = Modifier) {
    Row {
        Image(
            // 指定图片资源
            painterResource(id = R.drawable.ic_boy), contentDescription = null, modifier = Modifier
                // 设置高度为80dp，宽度为50dp
                .height(80.dp)
                .width(50.dp)
        )

        Spacer(Modifier.width(10.dp))

        Image(
            painterResource(id = R.drawable.ic_boy), contentDescription = null, modifier = Modifier
                // width、height 同时设置为100dp
                .size(100.dp)
                .clip(CircleShape)
        )
    }
}

@Composable
fun ModifierBackgroundDemo() {
    // 注意：传统View，background 能够设置 背景色 和 背景图片，
    // 而 Compose 的 background 只能设置颜色。 图片背景，需要使用 Box布局配合 Image 组合实现。
    val verticalGradientBrush = Brush.verticalGradient(
        colors = listOf(
            Color.Red, Color.Yellow, Color.White
        )
    )
    Row {
        Box(
            modifier = Modifier
                .size(50.dp)
                // 设置纯色背景
                .background(color = Color.Red)
        ) {
            Text("纯色", Modifier.align(Alignment.Center))
        }

        Spacer(Modifier.width(10.dp))

        Box(
            modifier = Modifier
                .size(50.dp)
                // 设置渐变色
                .background(brush = verticalGradientBrush)
        ) {
            Text("渐变色", Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun ModifierFillDemo() {
    Box(
        Modifier
            // 填充整个父布局(宽和高)
            .fillMaxSize()
            .background(Color.Red)
    )

    Box(
        Modifier
            // 高度填充父布局
            .fillMaxHeight()
            .width(60.dp)
            .background(Color.Red)
    )

    Box(
        Modifier
            // 宽度填充父布局
            .fillMaxWidth()
            .height(60.dp)
            .background(Color.Yellow)
    )
}

@Composable
fun ModifierBorderPaddingDemo() {
    // 注意：传统View，有 margin 和 padding 之分，Compose 中只有 padding 这一种修饰符
    Box(
        modifier = Modifier
            // padding 修饰组件的间隙
            .padding(5.dp) // 外间隙
            // border 修饰组件的边框，可以指定颜色、粗细、Shape 形状
            .border(2.dp, Color.Red, shape = RoundedCornerShape(2.dp)) // 边框
            .padding(10.dp) // 内间隙
    ) {
        Spacer(
            Modifier
                .size(width = 100.dp, height = 10.dp)
                .background(Color.Blue)
        )
    }
}

@Composable
fun ModifierOffsetDemo() {
    // Modifier offset 修饰符用来移动被修饰组件的位置（水平、垂直方向）
    // 注意：Modifier 调用顺序会影响最终UI呈现的效果
    Box(
        Modifier
            .size(100.dp)
            // 在原来显示的位置，重新修改x和y。x坐标移动100dp(向右)，y坐标移动20dp(向下)
            .offset(x = 100.dp, y = 20.dp)
            .background(Color.Gray)
    ) {
        Text("偏移量", Modifier.align(Alignment.Center))
    }
}

// <editor-fold defaultstate="collapsed" desc="Kotlin 跨域问题、作用域限定 Modifier 修饰符 ">

class AScope {
    fun visitA() {}
}

class BScope {
    fun visitB() {}
}

// 这段注释，详细解释了 Kotlin 语法：扩展函数类型 和  接收者对象
// 下面参数 scope 是 “receiver类型” 参数。
// AScope. 表示这个函数类型是与 AScope 类相关联的。它是一个扩展函数类型，意味着这个函数可以像 AScope 类的成员函数一样被调用。
// funA() 接受一个 AScope类型参数，并且这个函数在调用时，会自动获得一个 AScope 类型的 接收者对象。
// 接收者对象: 在 Kotlin 中，扩展函数类型允许你在函数体内直接访问接收者对象的成员。
fun funA(scope: AScope.() -> Unit) {
    // 下面 scope(AScope()) 这行代码，实际上是在调用传递进来的函数 scope，并且将 AScope() 作为接收者对象传递给它。
    // 这意味着当你调用 scope 时，它会在 AScope 的上下文中执行。
    scope(AScope())
}

// scope 是 “receiver类型” 参数
fun funB(scope: BScope.() -> Unit) {
    scope(BScope())
}

fun showKotlinScope() {
    // 本小段重点问题：Kotlin 跨域问题
    funA { //  第1层
        funB { // 第2层
            // “receiver类型” （如 funA() 函数的参数 scope: AScope.() -> Unit）默认可以跨级访问。
            // 在 B作用域中，可以访问 A作用域的函数 visitA()。这种属于一种“噪声”，加大出错的概率。
            // 可以通过 @LayoutScopeMarker 注解，避免 receiver 跨域访问。标注此注解后，只能访问自己作用域的函数。
            // @LayoutScopeMarker 能力来自 Kotlin @DslMarker 元注解
            // Compose 常用组件已经屏蔽跨域问题，如： RowScope、ColumnScope、BoxScope
            visitA()
        }
    }
}

// </editor-fold>

@Composable
fun ModifierMatchParentDemo() {
    Box(
        Modifier
            .fillMaxWidth()
            .height(50.dp)
            // 因为子布局的填满，所以父布局的黄色背景，并没有显示
            .background(Color.Yellow)
    ) {
        Box(
            Modifier
                // 设置当前组件与父组件尺寸相同
                .matchParentSize()
                // 设置当前组件与父组件尺寸所允许的最大尺寸
                //.fillMaxSize()
                .background(Color.LightGray)
        ) {
            Text("matchParentSize 与 fillMaxSize", Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun ModifierWeightDemo() {
    // 在 RowScope、ColumnScope 中，可以使用专属 weight 修饰符，按照百分比设置尺寸
    Row(
        Modifier
            .fillMaxWidth()
            .height(30.dp)
    ) {
        Box(
            Modifier
                .weight(0.25f)
                .fillMaxHeight()
                .background(Color.Blue)
        )
        Box(
            Modifier
                .weight(0.25f)
                .fillMaxHeight()
                .background(Color.Yellow)
        )
        Box(
            Modifier
                .weight(0.5f)
                .fillMaxHeight()
                .background(Color.White)
        ) {
            Text("Weight 权重", Modifier.align(Alignment.Center))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ViewPreview() {
    FragmentDemoView()
}
