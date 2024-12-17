package org.logan.compose.demo.ui.book.c2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.logan.compose.demo.base.fragment.BaseFragment

/**
 * desc: 第2章 常用基本组件 - 选择框 <br/>
 * time: 2024/12/10 14:46 <br/>
 * author: Logan <br/>
 * since: V 1.0 <br/>
 */
class C2_2_4_ComposeCheckboxFragment : BaseFragment() {

    @Composable
    override fun MyFragmentView() {
        C2_2_4_ComposeCheckboxSample()
    }

}

@Preview(showBackground = true)
@Composable
fun C2_2_4_ComposeCheckboxSamplePreview() {
    C2_2_4_ComposeCheckboxSample()
}

@Composable
fun C2_2_4_ComposeCheckboxSample(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
    ) {
        SimpleCheckboxSample()
        TriStateCheckboxSample()

        Spacer(Modifier.height(10.dp))

        TriStateCheckboxSample2()
        SwitchSample()
    }
}

@Composable
fun SimpleCheckboxSample() {
    val checkboxState = remember { mutableStateOf(true) }

    Checkbox(checked = checkboxState.value, onCheckedChange = {
        checkboxState.value = it
    }, colors = CheckboxDefaults.colors(checkedColor = Color(0xFF0079D3)))
}

@Composable
fun TriStateCheckboxSample() {
    // 定义两个 CheckBox 状态
    val (state, onStateChange) = remember { mutableStateOf(true) }
    val (state2, onStateChange2) = remember { mutableStateOf(true) }

    // 根据子 CheckBox 的状态设置 TriStateCheckBox 的状态
    val parentState = remember(state, state2) {
        if (state && state2) {
            ToggleableState.On
        } else if (!state && !state2) {
            ToggleableState.Off
        } else {
            ToggleableState.Indeterminate
        }
    }

    // 统一选择和取消
    TriStateCheckbox(
        //
        state = parentState,
        // 为从属的复选框设置状态
        onClick = {
            val isStateOn = (parentState != ToggleableState.On)
            onStateChange(isStateOn)
            onStateChange2(isStateOn)
        }, colors = CheckboxDefaults.colors(
            checkedColor = MaterialTheme.colorScheme.primary
        )
    )

    Column(Modifier.padding(start = 10.dp)) {
        Checkbox(state, onStateChange)
        Checkbox(state2, onStateChange2)
    }
}

@Composable
fun TriStateCheckboxSample2() {
    val childCheckedStates = remember { mutableStateListOf(false, false, false) }
    val parentState = when {
        childCheckedStates.all { it } -> ToggleableState.On
        childCheckedStates.none { it } -> ToggleableState.Off
        else -> ToggleableState.Indeterminate
    }

    Row {
        Text("Select all", modifier = Modifier.align(Alignment.CenterVertically))
        TriStateCheckbox(state = parentState, onClick = {
            val newState = (parentState != ToggleableState.On)
            childCheckedStates.forEachIndexed { index, _ ->
                // 统一修改 CheckBox 的 checked，变更选择状态。该方式，不会导致 onCheckedChange() 函数执行
                childCheckedStates[index] = newState
            }
        })
    }

    childCheckedStates.forEachIndexed { index, checked ->
        Row(
            modifier = Modifier.padding(start = 35.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Item ${index + 1}")
            Checkbox(checked = checked, onCheckedChange = { isChecked ->
                childCheckedStates[index] = isChecked
            })
        }
    }

}

@Composable
fun SwitchSample() {
    Row() {
        // 1，简单 switch
        val state = remember { mutableStateOf(false) }
        Switch(checked = state.value, onCheckedChange = { state.value = it })

        Spacer(modifier = Modifier.width(10.dp))

        // 2，自定义 switch
        val state2 = remember { mutableStateOf(false) }
        Switch(
            checked = state2.value, onCheckedChange = { state2.value = it },
            // 自定义样式
            thumbContent = {
                if (state2.value) {
                    Icon(Icons.Filled.Check, contentDescription = null)
                } else {
                    // Icon(Icons.Filled.Close, contentDescription = null)
                    null
                }
            },
            // 自定义颜色
            colors = SwitchDefaults.colors(
                // 选中图标背景色
                checkedThumbColor = Color(color = 0xFFFF6E15),
                // 选中容器背景色
                checkedTrackColor = Color(color = 0xD8A0A8BA),
                // 未选中图标颜色
                uncheckedThumbColor = Color(color = 0xFF06B4D6),
                // 未选中容器背景色
                uncheckedTrackColor = Color(color = 0x5501C52F)
            )
        )
    }

}


