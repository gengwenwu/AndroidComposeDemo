package org.logan.compose.demo.ui.book.c3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.logan.compose.demo.base.activity.BaseActivity
import org.logan.compose.demo.ui.theme.AndroidComposeDemoTheme
import org.logan.compose.demo.ui.theme.Medium
import org.logan.compose.demo.ui.theme.Purple500
import org.logan.compose.demo.ui.theme.Small
import org.logan.compose.demo.utils.extension.showMsg

/**
 * desc: Bloom - 登录页面 <br/>
 * time: 2024/12/23 18:53 <br/>
 * author: Logan <br/>
 * since: V 1.0 <br/>
 */
class BloomLoginActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidComposeDemoTheme {
                LoginPage()
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun LoginPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        LoginTitle()
        LoginInputBox()
        HintWithUnderline()
        LoginButton()
    }
}

@Composable
fun LoginTitle() {
    Text(
        text = "Log in with email",
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 184.dp),
        textAlign = TextAlign.Center,
        color = Color.Gray
    )
}

@Composable
fun LoginInputBox() {
    var emailText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }

    Spacer(modifier = Modifier.height(12.dp))
    OutlinedTextField(value = emailText, onValueChange = {
        emailText = it
    }, modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .clip(Small), placeholder = {
        Text(
            text = "Email Address", fontSize = 12.sp, modifier = Modifier.padding(top = (0).dp)
        )
    }, textStyle = TextStyle(fontSize = 12.sp),
        // 最多1行
        maxLines = 1,
        // 禁止换行
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        )
    )

    Spacer(modifier = Modifier.height(8.dp))
    OutlinedTextField(value = passwordText, onValueChange = {
        passwordText = it
    }, modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .clip(Small), placeholder = {
        Text(text = "Password (8+ characters)", fontSize = 12.sp)
    }, textStyle = TextStyle(fontSize = 12.sp),
        // 最多1行
        maxLines = 1,
        // 禁止换行
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        // 密码输入框
        visualTransformation = PasswordVisualTransformation()
    )
}

@Composable
fun HintWithUnderline() {
    val urlTag = "URL"

    val hintText = buildAnnotatedString {
        val defaultSpanStyle = SpanStyle(color = Color.Gray, fontSize = 12.sp)
        val underlineSpanStyle = SpanStyle(
            color = Color.Blue, fontSize = 12.sp, textDecoration = TextDecoration.Underline
        )
        // ParagraphStyle 设置段落的行高
        withStyle(style = ParagraphStyle(lineHeight = 20.sp)) {
            withStyle(style = defaultSpanStyle) {
                append("By clicking below, you agree to our ")
            }

            pushStringAnnotation(urlTag, "click 'terms of Use' ")
            withStyle(style = underlineSpanStyle) {
                append("Terms of Use")
            }
            pop()

            withStyle(style = defaultSpanStyle) {
                append(" and consent to our ")
            }

            pushStringAnnotation(urlTag, "click 'Privacy Policy'")
            withStyle(style = underlineSpanStyle) {
                append("Privacy Policy")
            }
            pop()
        }
    }

    Spacer(modifier = Modifier.height(8.dp))
    val context = LocalContext.current

    ClickableText(text = hintText, onClick = { offset ->
        hintText.getStringAnnotations(tag = urlTag, offset, offset).firstOrNull()?.let {
            when (it.tag) {
                urlTag -> {
                    // 点击事件
                    context.showMsg("${it.tag} ${it.item}")
                }
            }
        }
    })
}

@Composable
fun LoginButton() {
    Spacer(modifier = Modifier.height(20.dp))
    val context = LocalContext.current

    Button(
        onClick = {
            (context as Activity).startActivity(Intent(context, BloomHomeActivity::class.java))
        },
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth()
            .clip(Medium),
        colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = Purple500)
    ) {
        Text("Lon in", fontSize = 16.sp)
    }
}
