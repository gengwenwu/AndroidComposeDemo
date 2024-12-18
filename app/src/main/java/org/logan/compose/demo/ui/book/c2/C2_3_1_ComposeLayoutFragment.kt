package org.logan.compose.demo.ui.book.c2

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.logan.compose.demo.R
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
        Spacer(Modifier.height(8.dp))

        RowSample()
        Spacer(Modifier.height(8.dp))

        BoxSample()
        Spacer(Modifier.height(8.dp))

        SurfaceSample()
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
            .size(width = 260.dp, height = 80.dp),
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

@Composable
fun RowSample() {
    Surface(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        shadowElevation = 10.dp
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = "Jetpack Compose是什么？", fontSize = 20.sp)
            Spacer(modifier = Modifier.padding(vertical = 5.dp))
            Text(
                text = "Jetpack Compose是用于构建原生 Android UI的新工具包。它可简化并加速 Android UI 开发，更少的代码、强大的工具。",
                style = MaterialTheme.typography.bodyMedium
            )

            // Row 组件能够将内部的子项按照从左到右的水平方向进行排列。
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                // 水平方向的排列方式, 默认为 Arrangement.Start。
                // 总共7种，分别是：Start、Center、End、SpaceAround、SpaceBetween、SpaceEvenly、EqualWeight
                // 具体见：https://developer.android.com/develop/ui/compose/layouts/flow?hl=zh-cn 效果
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.Favorite, contentDescription = null)
                }
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.Call, contentDescription = null)
                }
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.Done, contentDescription = null)
                }
            }

        }
    }
}

@Composable
fun BoxSample() {
    // Box 组件将里面的子组件依次按照顺序堆叠的布局，类似于传统 FrameLayout
    Box {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Green)
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.Red)
            ) {
                Text("世界")
            }
        }
    }
}

@Composable
fun SurfaceSample() {
    // Surface 是一个平面，将很多组件放在这个平面上，设置这个平面的形状、颜色、阴影、点击事件等。
    // Surface 可以帮助我们更好的解耦一些代码，而不必在单个组件上添加更多的 Modifier 修饰符方法。
    // Surface 与 Box 区别：
    //      Surface：适合设置 形状、颜色、阴影、点击 等，其中阴影、点击事件，Box 是不能设置的。
    //      Box：适合设置 背景、颜色、大小。
    Surface(
        modifier = Modifier.size(width = 300.dp, height = 100.dp),
        shape = RoundedCornerShape(25.dp),
        // 背景色
        // color = Color(0xFF00BDE5),
        // 阴影
        shadowElevation = 10.dp,
    ) {
        Row(modifier = Modifier
            .fillMaxSize()
            .clickable {
                Log.v("TLog", "====== 点击了 Surface -> Row ")
            }) {
            Image(
                painter = painterResource(id = R.drawable.ic_left_boy),
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.fillMaxHeight()) {
                Text(
                    "留守儿童",
                    modifier = Modifier.padding(start = 8.dp, top = 10.dp),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    "迷失的童年, 失去的未来",
                    modifier = Modifier.padding(start = 8.dp, top = 12.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}