package org.logan.compose.demo.ui.book.c2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import org.logan.compose.demo.R
import org.logan.compose.demo.base.fragment.BaseFragment

/**
 * desc: 约束布局案例 <br/>
 * time: 2024/12/18 17:24 <br/>
 * author: Logan <br/>
 * since: V 1.0 <br/>
 */
class C2_3_4_ConstraintLayoutFragment : BaseFragment() {

    @Composable
    override fun MyFragmentView() {
        C2_3_4_ConstraintLayoutSample()
    }

}

@Preview
@Composable
fun C2_3_4_ConstraintLayoutSamplePreview() {
    C2_3_4_ConstraintLayoutSample()
}

@Composable
fun C2_3_4_ConstraintLayoutSample() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 使用 ConstraintLayout 布局，两步骤：创建引用 和 绑定引用
        // 1，两种创建引用的方式，createRef() 和 createRefs()，该函数只能在 ConstraintLayoutScope 中使用
        //  (1). 创建单个约束引用
        //      val portraitImageRef = remember { createRef() }
        //      val usernameTextRef = remember { createRef() }

        //  (2). 创建多个约束引用
        //      val (portraitImageRef, usernameTextRef) = remember { createRefs() }

        // 2，绑定约束引用
        //      Modifier.constrainAs(portraitImageRef) // 该函数只能在 ConstraintLayoutScope 中使用

        SimpleConstraintLayoutSample()

    }
}

@Composable
fun SimpleConstraintLayoutSample() {
    ConstraintLayout(
        modifier = Modifier
            .shadow(2.dp, shape = RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .height(100.dp)
            .padding(10.dp)
    ) {
        // 1.创建多个约束引用
        val (portraitImageRef, usernameTextRef, desTextRef) = remember { createRefs() }

        Image(painter = painterResource(R.drawable.ic_boy),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                // 2.绑定约束引用
                .constrainAs(portraitImageRef) {
                    // parent 表示父布局的约束引用
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                })

        Text("Compose 技术爱好者",
            fontSize = 16.sp,
            modifier = Modifier.constrainAs(usernameTextRef) {
                top.linkTo(parent.top)
                start.linkTo(portraitImageRef.end, margin = 10.dp)
            })

        Text("我的个人描述, 看看书、看看剧。比如：《百年孤独》、《豺狼的日子》、《权利的游戏》",
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(desTextRef) {
                // text top.linkTo 表示与 usernameTextRef 的底部对齐，margin 表示间距
                top.linkTo(usernameTextRef.bottom, margin = 10.dp)
                start.linkTo(usernameTextRef.start)
                end.linkTo(parent.end, margin = 16.dp)
                // 也可以在 ConstrainScope 中指定组件的宽高，直接设置 width 和 height 即可。
                // Dimension 可选值有：wrapContent(默认)、matchParent、fillToConstraints、 preferredWrapContent、ratio、percent、value、preferredValue
                width = Dimension.fillToConstraints
            })
    }
}

