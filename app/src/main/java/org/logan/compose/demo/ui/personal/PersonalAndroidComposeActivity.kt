package org.logan.compose.demo.ui.personal

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.logan.compose.demo.base.activity.BaseActivity
import org.logan.compose.demo.base.activity.FillTopBarModifier
import org.logan.compose.demo.ui.theme.AndroidComposeDemoTheme

/**
 * desc: 个人测试 Compose Demo <br/>
 * time: 2024/12/9 18:32 <br/>
 * author: Logan <br/>
 * since: V 1.0 <br/>
 */
class PersonalAndroidComposeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AndroidComposeDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column((FillTopBarModifier(innerPadding).fillMaxWidth())) {
                        HelloText(Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }

}

@Composable
fun HelloText(modifier: Modifier = Modifier) {
    Text(
        text = "个人测试 Compose Demo"
    )
}

@Preview(showBackground = true)
@Composable
fun ActivityPreview() {
    AndroidComposeDemoTheme {
        HelloText()
    }
}
