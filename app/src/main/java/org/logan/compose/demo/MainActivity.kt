package org.logan.compose.demo

import android.app.Activity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.logan.compose.demo.base.activity.BaseActivity
import org.logan.compose.demo.base.activity.FillTopBarModifier
import org.logan.compose.demo.base.activity.goNextPage
import org.logan.compose.demo.ui.book.BookAndroidComposeActivity
import org.logan.compose.demo.ui.google.GoogleAndroidComposeActivity
import org.logan.compose.demo.ui.personal.PersonalAndroidComposeActivity
import org.logan.compose.demo.ui.theme.AndroidComposeDemoTheme


class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AndroidComposeDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DemoMenu(FillTopBarModifier(innerPadding))
                }
            }
        }
    }

}

@Composable
fun DemoMenu(modifier: Modifier = Modifier) {
    val buttonModifier = Modifier.padding(start = 16.dp, end = 16.dp)
    val activity = LocalContext.current as Activity
    // val scope = rememberCoroutineScope()

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Button(modifier = buttonModifier, onClick = {
            goNextPage(activity, BookAndroidComposeActivity::class.java)
        }) {
            Text("《Android Compose 从入门到实践案例》")
        }
        Button(modifier = buttonModifier, onClick = {
            goNextPage(activity, GoogleAndroidComposeActivity::class.java)
        }) {
            Text("Android Compose 官方Demo")
        }
        Button(modifier = buttonModifier, onClick = {
            goNextPage(activity, PersonalAndroidComposeActivity::class.java)
        }) {
            Text("Personal测试 Compose Demo")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DemoMenuPreview() {
    AndroidComposeDemoTheme {
        DemoMenu()
    }
}

//@Composable
//fun CurrentActivity(): Activity {
//    val context = LocalContext.current
//    return remember(context) {
//        context as Activity
//    }
//}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!", modifier = modifier
//    )
//}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    AndroidComposeDemoTheme {
//        Greeting("Android")
//    }
//}