package org.logan.compose.demo.ui.book

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.logan.compose.demo.base.activity.BaseActivity
import org.logan.compose.demo.base.activity.FillTopBarModifier
import org.logan.compose.demo.ui.theme.AndroidComposeDemoTheme

/**
 * desc: 《Android Compose 从入门到实践案例》 <br/>
 * time: 2024/12/9 18:32 <br/>
 * author: Logan <br/>
 * since: V 1.0 <br/>
 */
class BookAndroidComposeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AndroidComposeDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LazyColumn(FillTopBarModifier(innerPadding).fillMaxWidth()) {
                        item {
                            HelloText()
                        }
                    }
                }
            }
        }
    }

    private fun data() {
        val courseMap = mapOf(
            "C2 常用UI组件" to "",
            "C3 主题" to "",
            "C4 状态管理与重组" to "",
            "C5 动画" to "",
            "C6 手势处理" to "",
            "C7 页面导航" to "",
            "C8 TODO" to ""
        )
    }
}

@Composable
fun HelloText(modifier: Modifier = Modifier) {
    Text(
        text = "《Android Compose 从入门到实践案例》"
    )
}


@Preview(showBackground = true)
@Composable
fun ActivityPreview() {
    AndroidComposeDemoTheme {
        HelloText()
    }
}
