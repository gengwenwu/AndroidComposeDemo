package org.logan.compose.demo.ui.book.c3

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
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


// 底部导航
val navList = listOf(
    Pair("Home", R.drawable.ic_home),
    Pair("Favorites", R.drawable.ic_favorite_border),
    Pair("Profile", R.drawable.ic_account_circle),
    Pair("Cart", R.drawable.ic_shopping_cart)
)

// banner
val bloomBannerList = listOf(
    Pair("Desert chic", R.drawable.desert_chic),
    Pair("Tiny terrariums", R.drawable.tiny_terrariums),
    Pair("Jungle Vibes", R.drawable.jungle_vibes)
)

// 底部 info
val bloomInfoList = listOf(
    Pair("Monstera", R.drawable.monstera),
    Pair("Aglaonema", R.drawable.aglaonema),
    Pair("Peace lily", R.drawable.peace_lily),
    Pair("Fiddle leaf tree", R.drawable.fiddle_leaf),
    Pair("Desert chic", R.drawable.desert_chic),
    Pair("Tiny terrariums", R.drawable.tiny_terrariums),
    Pair("Jungle Vibes", R.drawable.jungle_vibes)
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
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
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
    Spacer(modifier = Modifier.height(16.dp))
    Text("Browse themes", fontSize = 18.sp, fontFamily = FontFamily(Font(R.font.alibaba_heavy)))

    LazyRow(modifier = Modifier.padding(top = 8.dp)) {
        itemsIndexed(bloomBannerList) { index, item ->
            if (index != 0) {
                Spacer(modifier = Modifier.width(8.dp))
            }

            PlantCard(item.first, item.second)
        }
    }
}

@Composable
fun PlantCard(plant: String, image: Int) {
    // 写法1
    Card(
        modifier = Modifier
            .size(136.dp)
            .clip(RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors().copy(containerColor = Color.White),
        border = BorderStroke((0.2).dp, Color.Gray),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Image(
            painter = painterResource(image),
            modifier = Modifier
                .fillMaxWidth()
                .height(96.dp),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Text(
            plant,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight(),
            fontSize = 14.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }

    // 写法2
//    Card(
//        modifier = Modifier.size(136.dp),
//        border = BorderStroke((0.2).dp, Color.Gray),
//        shape = RoundedCornerShape(16.dp),
//        elevation = CardDefaults.cardElevation(2.dp)
//    ) {
//        Column(
//            Modifier.background(Color.White)
//        ) {
//            Image(
//                painterResource(id = image),
//                contentScale = ContentScale.Crop,
//                contentDescription = "image",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(96.dp)
//            )
//            Box(
//                modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
//            ) {
//                Text(
//                    text = plant,
//                    color = Gray,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .paddingFromBaseline(top = 24.dp, bottom = 16.dp),
//                    textAlign = TextAlign.Center
//                )
//            }
//        }
//    }

}

@Composable
fun BloomInfoList() {
    Spacer(modifier = Modifier.height(16.dp))

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            "Design your home garden",
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.alibaba_heavy))
        )

        Icon(
            ImageVector.vectorResource(R.drawable.ic_filter_list),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }

    LazyColumn(
        modifier = Modifier.padding(top = 8.dp),
        // 整个列表最下面边距为 28.dp
        contentPadding = PaddingValues(bottom = 28.dp),
        // 列表之间的间距为 8.dp
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(bloomInfoList) {
            DesignCard(it.first, it.second)
        }
    }
}

@Composable
fun DesignCard(plant: String, image: Int) {
    val rowSize = 64.dp
    var checkState by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .height(rowSize)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier
                .size(rowSize)
                .clip(RoundedCornerShape(6.dp)),
            contentScale = ContentScale.Crop
        )

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(rowSize)
        ) {
            val (nameTextRef, descTextRef, checkboxRef, dividerRef) = createRefs()

            Text(text = plant,
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(nameTextRef) {
                    start.linkTo(parent.start, 8.dp)
                    top.linkTo(parent.top, 8.dp)
                })

            Text(text = "This is a description of the plant",
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.constrainAs(descTextRef) {
                    start.linkTo(nameTextRef.start)
                    top.linkTo(nameTextRef.bottom)
                })

            Checkbox(checked = checkState, onCheckedChange = {
                checkState = !checkState
            }, modifier = Modifier.constrainAs(checkboxRef) {
                end.linkTo(parent.end, 8.dp)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            })

            HorizontalDivider(modifier = Modifier.constrainAs(dividerRef) {
                start.linkTo(parent.start, 8.dp)
                bottom.linkTo(parent.bottom)
            }, color = Color.Black, thickness = 0.5.dp)
        }
    }
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
