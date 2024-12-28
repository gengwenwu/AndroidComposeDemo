package org.logan.compose.demo.ui.book.c2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.logan.compose.demo.base.fragment.BaseFragment

/**
 * desc: 列 案例 <br/>
 * time: 2024/12/20 13:45 <br/>
 * author: Logan <br/>
 * since: V 1.0 <br/>
 */
class C2_4_ColumnFragment : BaseFragment() {

    @Composable
    override fun MyFragmentView() {
        C2_4_ColumnFragmentSamplePreview()
    }

}

@Preview(showBackground = true)
@Composable
fun C2_4_ColumnFragmentSamplePreview() {
    C2_4_ColumnFragmentSample()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun C2_4_ColumnFragmentSample() {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
    ) {
        DropdownMenuSample()
        Spacer(modifier = Modifier.height(12.dp))

        LazyColumnSample()
        Spacer(modifier = Modifier.height(12.dp))

        LazyColumnSample2()
        Spacer(modifier = Modifier.height(12.dp))

        LazyItemSpaceSample()
    }
}

@Composable
fun DropdownMenuSample() {
    val dropdownMenuState = remember { mutableStateOf(false) }
    val options = listOf("添加好友", "更换背景", "隐私协议")

    Button(onClick = {
        dropdownMenuState.value = true
    }) {
        Text("DropdownMenu 案例")
    }

    if (dropdownMenuState.value) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red)
        ) {
            DropdownMenu(expanded = true, onDismissRequest = {
                dropdownMenuState.value = false
            }) {
                Column(modifier = Modifier.background(Color.Red)) {
                    options.forEach { option ->
                        ListItem(headlineContent = { Text(option) })
                    }
                }
            }
        }
    }
}

@Composable
fun LazyColumnSample() {
    // LazyColumn 和 LazyRow 内部都是基于 LazyList 组件实现的。与其它组件不同，不能在 LazyList 里面直接裸写子 Composable，需要通过 item
    LazyColumn(
        modifier = Modifier
            .height(110.dp)
            .fillMaxWidth()
            .background(Color.LightGray)
    ) {
        item {
            Text("第1项内容")
        }
        items(15) {
            Text("第${it + 2}项内容")
        }
        item {
            Text("最后一项")
        }
    }
}

@Composable
fun LazyColumnSample2() {
    val options =
        listOf("天水麻辣烫", "重庆小面", "北京豆花", "淮南牛肉汤", "新疆牛肉拉面", "北京烤鸭")

    LazyColumn(
        modifier = Modifier
            .height(110.dp)
            .fillMaxWidth()
            .background(Color.LightGray)
    ) {
        // items 支持 size、List、Array
        items(options) {
            Text(it)
        }
        item {
            Text("...")
        }
    }
}

@Composable
fun LazyItemSpaceSample() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(Color.LightGray),
        // 指定列表边距(上下左右)
        contentPadding = PaddingValues(25.dp),
        // 指定列表项垂直间距
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(50) {
            ContentCard(it)
        }
    }
}

@Composable
fun ContentCard(index: Int) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 15.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp), contentAlignment = Alignment.Center
        ) {
            Text(
                text = "我是序列号$index 位的卡片", style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
