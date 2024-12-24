package org.logan.compose.demo.ui.book.c3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.logan.compose.demo.R
import org.logan.compose.demo.base.activity.BaseActivity
import org.logan.compose.demo.ui.theme.AndroidComposeDemoTheme
import org.logan.compose.demo.ui.theme.Medium
import org.logan.compose.demo.ui.theme.Pink40
import org.logan.compose.demo.ui.theme.Pink80
import org.logan.compose.demo.ui.theme.Purple500

/**
 * desc: Bloom - welcome 页面 <br/>
 * time: 2024/12/23 18:53 <br/>
 * author: Logan <br/>
 * since: V 1.0 <br/>
 */
class BloomWelcomeActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidComposeDemoTheme {
                WelcomePage()
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun WelcomePage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Pink80)
    ) {
        Image(
            // 使用 rememberVectorPainter 可以避免每次组合时重新加载矢量图形，从而提高性能
            painter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_light_welcome_bg)),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        WelcomeContent()
    }
}

@Composable
fun WelcomeContent() {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(72.dp))
        LeftImage()
        Spacer(modifier = Modifier.height(48.dp))
        WelcomeTitle()
        Spacer(modifier = Modifier.height(40.dp))
        WelcomeButtons()
    }
}

@Composable
fun LeftImage() {
    Image(
        painter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_light_welcome_illos)),
        contentDescription = null,
        modifier = Modifier
            .wrapContentSize()
            .padding(start = 85.dp)
    )
}

@Composable
fun WelcomeTitle() {
    Image(
        painter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_light_logo)),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp)
    )

    Text(
        text = "Beautiful home garden solutions",
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp),
        fontSize = 16.sp,
        fontFamily = FontFamily.SansSerif,
        textAlign = TextAlign.Center
    )
}

@Composable
fun WelcomeButtons() {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Button(
        onClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 30.dp)
            .clip(Medium),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White, containerColor = Purple500
        )
    ) {
        Text(text = "Create account", fontSize = 16.sp)
    }

    Spacer(modifier = Modifier.height(6.dp))

    TextButton(
        onClick = {
            scope.launch {
                (context as Activity).startActivity(Intent(context, BloomLoginActivity::class.java))
            }
        }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.textButtonColors(
            contentColor = Pink40
        )
    ) {
        Text("Log in", fontSize = 14.sp)
    }

}
