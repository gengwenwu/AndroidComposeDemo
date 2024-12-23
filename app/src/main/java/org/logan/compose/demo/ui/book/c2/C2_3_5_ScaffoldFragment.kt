package org.logan.compose.demo.ui.book.c2

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.logan.compose.demo.base.fragment.BaseFragment

/**
 * desc: 脚手架 Scaffold 案例 <br/>
 * time: 2024/12/20 13:45 <br/>
 * author: Logan <br/>
 * since: V 1.0 <br/>
 */
class C2_3_5_ScaffoldFragment : BaseFragment() {

    @Composable
    override fun MyFragmentView() {
        C2_3_5_ScaffoldFragmentSample()
    }

}

@Preview
@Composable
fun C2_3_5_ScaffoldFragmentSamplePreview() {
    C2_3_5_ScaffoldFragmentSample()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun C2_3_5_ScaffoldFragmentSample() {
    val barItems = listOf(
        Icons.Filled.Check, Icons.Filled.Call, Icons.Filled.AccountBox
    )

    // Scaffold 组件，它是一个容器，它提供了一个标准的 Android 应用界面，它包含了：标题栏、底部栏、悬浮按钮、抽屉等。
    Scaffold(
        // 顶部 bar。有4种类型：小、居中对其、中等、大。见：https://developer.android.google.cn/develop/ui/compose/components/app-bars?hl=zh-cn
        topBar = {
            // 这里可以完全自定义
            TopAppBar(
                // 左边标题栏
                title = {
                    Text(text = "主页", style = MaterialTheme.typography.headlineMedium)
                },
                // 左上角导航栏
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.Menu, contentDescription = null)
                    }
                },
                // 右边按钮区域
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.Edit, contentDescription = null)
                    }
                },
                // 颜色
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        },
        // 底部 bar
        bottomBar = {
            // 这里可以完全自定义
            BottomAppBar(modifier = Modifier.height(50.dp)) {
                barItems.forEach {
                    IconButton(onClick = {}) {
                        Icon(it, contentDescription = null)
                    }
                }
            }
        },
        // 悬浮按钮
        floatingActionButton = {
            FloatingActionButton(
                onClick = {}, elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(Icons.Filled.Add, contentDescription = null)
            }
        }) { innerPadding ->
        // 主页内容
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "主页界面")
        }
    }

}
