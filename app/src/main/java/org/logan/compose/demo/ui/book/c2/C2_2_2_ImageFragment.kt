package org.logan.compose.demo.ui.book.c2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.sharp.AccountBox
import androidx.compose.material.icons.twotone.AccountBox
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.logan.compose.demo.R
import org.logan.compose.demo.base.fragment.BaseFragment

/**
 * desc: 第2章 常用基本组件 - 图片 <br/>
 * time: 2024/12/10 14:46 <br/>
 * author: Logan <br/>
 * since: V 1.0 <br/>
 */
class C2_2_2_ImageFragment : BaseFragment() {

    @Composable
    override fun MyFragmentView() {
        C2_2_2_ComposeImageSample()
    }

}

@Preview(showBackground = true)
@Composable
fun C2_2_2_ComposeImageSamplePreview() {
    C2_2_2_ComposeImageSample()
}

@Composable
fun C2_2_2_ComposeImageSample(modifier: Modifier = Modifier) {
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        item {
            IconSampleFromIconLib()
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            IconSupportTypeSample()
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            ImageSample()
            Spacer(modifier = Modifier.height(.10.dp))
        }
    }
}

@Composable
fun IconSampleFromIconLib() {
    // Icon 组件用于显示一些列小图标, Google 内置了5种类型图标(svg)，在 Icons.xxx.xxx，5种分别是：Outlined、Filled、Rounded、Sharp、TwoTone。
    // 更多见 https://fonts.google.com/icons，更多icon 可以引入 "androidx.compose.material:material-icons-extended:xxx"。
    // 大图标, Icon组件是无法显示的，黑色背景
    Row(
        modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        // 矢量图对象，使用 svg 格式图标
        Icon(
            imageVector = Icons.Outlined.AccountBox,
            contentDescription = null,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Filled.AccountBox,
            contentDescription = null,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Rounded.AccountBox,
            contentDescription = null,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Sharp.AccountBox,
            contentDescription = null,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.TwoTone.AccountBox,
            contentDescription = null,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun IconSupportTypeSample() {
    // Icon 支持三种不同类型的图片设置 (imageVector、bitmap、painter)
    // 非指定 tint 情况下，仅显示黑白色，下面原始图片都是彩色，但是显示都是黑白
    Row(
        modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        // 矢量图对象，可以使用 svg 格式的图标
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_error),
            contentDescription = "矢量图资源",
            modifier = Modifier.weight(1f)
        )

        // 位图对象，png、jpg 图标
        Icon(
            bitmap = ImageBitmap.imageResource(id = R.drawable.ic_video_pause),
            contentDescription = "本地图片资源",
            modifier = Modifier.weight(1f)
        )

        // 自定义画笔，在 canvas 上直接绘制图标
        Icon(
            painter = painterResource(R.drawable.ic_video_play),
            contentDescription = "任意图片资源",
            modifier = Modifier.weight(1f),
            // 修改背景色
            tint = Color.Red
        )
    }
}

@Composable
fun ImageSample() {
    // image 显示图片，它也支持三种不同类型的图片设置 (imageVector、bitmap、painter)
    Row(
        modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        // svg
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_error),
            contentDescription = null,
            modifier = Modifier.weight(1f)
        )

        // jpg、png
        Image(
            bitmap = ImageBitmap.imageResource(R.drawable.ic_video_pause),
            contentDescription = null,
            modifier = Modifier.weight(1f)
        )

        // 自定义画笔
        Image(
            painter = painterResource(R.drawable.ic_video_play),
            contentDescription = null,
            modifier = Modifier
                //.size(50.dp)
                .weight(1f),
            // 对其方式
            alignment = Alignment.CenterEnd,
            // 图片裁剪方式，7种，Crop 类似于 ScaleType.centerCrop
            contentScale = ContentScale.Fit
        )
    }
}



