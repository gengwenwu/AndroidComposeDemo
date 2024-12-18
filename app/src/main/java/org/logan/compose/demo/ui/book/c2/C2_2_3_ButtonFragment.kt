package org.logan.compose.demo.ui.book.c2

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.logan.compose.demo.base.fragment.BaseFragment

/**
 * desc: 第2章 常用基本组件 - 按钮 <br/>
 * time: 2024/12/10 14:46 <br/>
 * author: Logan <br/>
 * since: V 1.0 <br/>
 */
class C2_2_3_ButtonFragment : BaseFragment() {

    @Composable
    override fun MyFragmentView() {
        C2_2_3_ComposeButtonSample()
    }

}

@Preview(showBackground = true)
@Composable
fun C2_2_3_ComposeButtonSamplePreview() {
    C2_2_3_ComposeButtonSample()
}

@Composable
fun C2_2_3_ComposeButtonSample(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
    ) {
        ButtonSample()
        InteractionSourceButtonSample()
        IconButtonSample()
        FloatingActionButtonSample()
    }
}

@Composable
fun ButtonSample() {
    //  Button 没有任何UI，只有 onClick 事件，它的 UI 需要其它组件实现
    Button(onClick = {

    }, colors = ButtonDefaults.filledTonalButtonColors().copy(containerColor = Color.LightGray)) {
        // content 是一个 RowScope
        Icon(
            imageVector = Icons.Filled.Done,
            contentDescription = null,
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(Modifier.width(ButtonDefaults.IconSpacing))
        Text("确认")
    }
}

@Composable
fun InteractionSourceButtonSample() {
    val interactionSource = remember { MutableInteractionSource() }
    // 获取按钮的按下状态
    val pressState = interactionSource.collectIsPressedAsState()
    // 获取按钮的焦点状态
    // val focusedState = interactionSource.collectIsFocusedAsState()
    // 获取按钮的拖拽状态
    // val draggedState = interactionSource.collectIsDraggedAsState()
    // 获取按钮的悬停状态
    // val hoveredState = interactionSource.collectIsHoveredAsState()

    val borderColor = if (pressState.value) Color.Red else Color.Green

    Button(
        onClick = {
            // Button 并非唯一可点击组件，理论上任何 Composable组件都可以通过 Modifier.clickable 实现点击。
            // 而当 Button 被点击后,需要额外进行一些事件响应处理, 比如水波纹等, 这是其内部通过拦截 Modifier.clickable 事件实现的处理,
            // 由于 Modifier.clickable 已经被内部实现所占用, Button 需要提供单独的 onClick 参数供开发者使用。
            // 注意: Button 的 onClick 在底层是通过覆盖 Modifier.clickable 实现的，所以不要为 Button 设置 Modifier.clickable
            // 即使设置了, 也会因为被 onClick 覆盖而没有任何效果。
        }, border = BorderStroke(2.dp, borderColor),
        // 监听组件状态事件
        interactionSource = interactionSource
    ) {
        Text("Long Press")
    }

}

@Composable
fun IconButtonSample() {
    // IconButton 实际上是 Button 组件简单的封装，在 IconButton 提供了一个图标组件， 图标尺寸一般 24 * 24dp。
    IconButton(onClick = {

    }) {
        Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
    }
}

@Composable
fun FloatingActionButtonSample() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        // 1，普通大小 FloatingActionButton（简称FAB） 悬浮按钮，它也需要提供一个 Icon 组件。
        //  最主要3个属性：
        FloatingActionButton(
            // 点击事件
            onClick = {

            },
            // 容器颜色
            containerColor = Color.LightGray,
            // 内容颜色
            contentColor = Color.Blue
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = null)
        }
        Spacer(Modifier.width(10.dp))

        // 小型FAB
        SmallFloatingActionButton(onClick = {

        }) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = null)
        }
        Spacer(Modifier.width(10.dp))

        // 大型 FAB
        LargeFloatingActionButton(onClick = {

        }) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = null)
        }
        Spacer(Modifier.width(10.dp))

        // 文案扩展 FAB
        ExtendedFloatingActionButton(
            onClick = {},
            // 文案
            text = { Text("添加") },
            icon = { Icon(imageVector = Icons.Filled.Add, contentDescription = null) },
        )
    }
}


