package org.logan.compose.demo.ui.book.c3

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import org.logan.compose.demo.R
import org.logan.compose.demo.base.activity.BaseActivity
import org.logan.compose.demo.ui.theme.AndroidComposeDemoTheme
import org.logan.compose.demo.ui.theme.Gray
import org.logan.compose.demo.ui.theme.Pink100
import org.logan.compose.demo.ui.theme.White
import org.logan.compose.demo.utils.extension.showMsg

/**
 * desc: Bloom - 主页 <br/>
 * time: 2024/12/23 18:53 <br/>
 * author: Logan <br/>
 * since: V 1.0 <br/>
 */


val navList = listOf(
    Pair("Home", R.drawable.ic_home),
    Pair("Favorites", R.drawable.ic_favorite_border),
    Pair("Profile", R.drawable.ic_account_circle),
    Pair("Cart", R.drawable.ic_shopping_cart)
)

class BloomHomeActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidComposeDemoTheme {
                HomePage()
            }
        }

        hideSystemBars()
    }

    private fun hideSystemBars() {
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
    }
}

@Composable
fun HomePage() {
    Scaffold(bottomBar = {
        BottomBar()
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(White)
                .padding(16.dp)
        ) {
            SearchBar()
            BloomRowBanner()
            BloomInfoList()
        }
    }
}

@Composable
fun BottomBar() {
    val context = LocalContext.current

    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp), containerColor = Pink100
    ) {
        Row(
            horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()
        ) {
            navList.forEach {
                Column(
                    modifier = Modifier
                        // 1:1 比例占比
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable {
                            context.showMsg(it.first)
                        }, verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        it.first,
                        modifier = Modifier.fillMaxWidth(),
                        color = Gray,
                        fontSize = 12.sp,
                        lineHeight = 12.sp,
                        textAlign = TextAlign.Center
                    )
                    Icon(
                        painter = painterResource(it.second),
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.CenterHorizontally),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Composable
fun SearchBar() {
    var searchText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
    ) {
        TextField(
            value = searchText,
            onValueChange = {
                searchText = it
            },
            modifier = Modifier
                .fillMaxSize()
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp)),
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    modifier = Modifier
                        .size(18.dp)
                        .align(Alignment.Center),
                    contentDescription = null
                )
            },
            placeholder = {
                Text("Search", color = Color.Gray, fontSize = 16.sp)
            },
            colors = TextFieldDefaults.colors().copy(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            )
        )
    }

    //    BasicTextField(value = searchText,
//        onValueChange = {
//            searchText = it
//        },
//        modifier = Modifier
//            .height(56.dp)
//            .fillMaxWidth()
//            .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(8.dp)),
//        decorationBox = { innerTextField ->
//            Row(
//                verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxSize()
//            ) {
//                Icon(
//                    painter = painterResource(R.drawable.ic_search), modifier = Modifier
//                        .padding(start = 8.dp)
//                        .size(18.dp), contentDescription = null
//                )
//
//                if (searchText.isEmpty()) {
//                    Text(
//                        "Search",
//                        color = Color.Gray,
//                        fontSize = 16.sp,
//                        modifier = Modifier.padding(start = 8.dp)
//                    )
//                }
//
//                innerTextField()
//            }
//        })
}

@Composable
fun BloomRowBanner() {

}

@Composable
fun BloomInfoList() {

}

//@Preview
//@Composable
//fun BottomBarPreview() {
//    AndroidComposeDemoTheme {
//        BottomBar()
//    }
//}

@Preview
@Composable
fun HomePagePreview() {
    AndroidComposeDemoTheme {
        HomePage()
    }
}
