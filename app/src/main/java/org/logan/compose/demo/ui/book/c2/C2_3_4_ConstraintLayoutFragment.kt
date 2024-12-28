package org.logan.compose.demo.ui.book.c2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
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

@Preview(showBackground = true)
@Composable
fun C2_3_4_ConstraintLayoutSamplePreview() {
    C2_3_4_ConstraintLayoutSample()
}

@Composable
fun C2_3_4_ConstraintLayoutSample() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .height(100.dp)
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
        Spacer(Modifier.height(10.dp))

        ConstraintLayoutBarrierSample()
        Spacer(Modifier.height(10.dp))

        ConstraintLayoutGuidelineSample()
        Spacer(Modifier.height(10.dp))

        ConstraintLayoutChainSample()
    }
}

@Composable
fun SimpleConstraintLayoutSample() {
    ConstraintLayout(
        modifier = Modifier
            .shadow(1.dp)
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
            fontSize = 12.sp,
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

@Composable
fun ConstraintLayoutBarrierSample() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(1.dp)
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        val (userNameTextRef, passwordTextRef, usernameInputRef, passwordInputRef, dividerRef) = remember { createRefs() }
        // 1. 创建一个结尾分界线
        val barrier = createEndBarrier(userNameTextRef, passwordTextRef)

        Text(
            "用户名",
            modifier = Modifier
                .height(45.dp)
                .constrainAs(userNameTextRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .wrapContentHeight(Alignment.CenterVertically),
        )

        OutlinedTextField("",
            onValueChange = {},
            modifier = Modifier.constrainAs(usernameInputRef) {
                // 将左侧起始位置指定为分界线后10.dp的位置
                start.linkTo(barrier, 10.dp)
                top.linkTo(userNameTextRef.top)
                bottom.linkTo(userNameTextRef.bottom)
                // 高度根据约束条件来
                height = Dimension.fillToConstraints
            },
            label = {
                Text(text = "Barrier示例")
            })

        HorizontalDivider(thickness = 1.dp, modifier = Modifier.constrainAs(dividerRef) {
            top.linkTo(userNameTextRef.bottom, margin = 12.dp)
            start.linkTo(userNameTextRef.start)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
        })

        Text("密码",
            Modifier
                .height(45.dp)
                .wrapContentHeight(Alignment.CenterVertically)
                .constrainAs(passwordTextRef) {
                    top.linkTo(dividerRef.bottom, margin = 12.dp)
                    start.linkTo(userNameTextRef.start)
                })

        OutlinedTextField("", onValueChange = {}, Modifier.constrainAs(passwordInputRef) {
            start.linkTo(barrier, 10.dp)
            top.linkTo(passwordTextRef.top)
            bottom.linkTo(passwordTextRef.bottom)
            height = Dimension.fillToConstraints
        })
    }

}

@Composable
fun ConstraintLayoutGuidelineSample() {
    Box(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
    ) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.LightGray)
        ) {

            // 使用 guideline 不依赖任何引用，凭空创建一条引导线。 下面案例是从顶部出发，从上到下，占比布局30%案例
            val guideline = createGuidelineFromTop(0.3f)
            val (userPortraitBackgroundRef, userPortraitImgRef, welcomeRef) = remember { createRefs() }

            Box(modifier = Modifier
                .constrainAs(userPortraitBackgroundRef) {
                    top.linkTo(parent.top)
                    // box底部 与 guideline 对齐
                    bottom.linkTo(guideline)
                    height = Dimension.fillToConstraints
                    width = Dimension.matchParent
                }
                .background(Color(0xFF1E9FFF)))

            Image(painter = painterResource(R.drawable.ic_boy),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .constrainAs(userPortraitImgRef) {
                        // Image top、bottom 与 guideline 对齐，就是Image居中 guideline 位置
                        top.linkTo(guideline)
                        bottom.linkTo(guideline)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .size(60.dp)
                    .clip(CircleShape))

            Text("Compose guideline 示例",
                fontSize = 26.sp,
                color = Color.White,
                modifier = Modifier.constrainAs(welcomeRef) {
                    top.linkTo(userPortraitImgRef.bottom, margin = 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
        }
    }
}

@Composable
fun ConstraintLayoutChainSample() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(Color.LightGray)
    ) {
        val (quotesFirstLineRef, quotesSecondLineRef, quotesThirdLineRef, quotesForthLineRef) = remember { createRefs() }

        // createVerticalChain() 垂直方向上的约束链。createHorizontalChain(), 创建水平方向上的约束链。
        createVerticalChain(
            quotesFirstLineRef, quotesSecondLineRef, quotesThirdLineRef, quotesForthLineRef,
            // ChainStyle 提供三种 Chain Style ：
            //  1, ChainStyle.Spread: 所有元素平分  parent 空间
            //  2, ChainStyle.SpreadInside: 首位贴紧边界，剩余元素平分 parent 空间
            //  3, ChainStyle.Packed: 所有元素聚集在中间
            chainStyle = ChainStyle.Packed
        )

        Text("寄蜉蝣于天地，", modifier = Modifier.constrainAs(quotesFirstLineRef) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
        Text("渺沧海之一粟。", modifier = Modifier.constrainAs(quotesSecondLineRef) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
        Text("哀吾生之须臾，", modifier = Modifier.constrainAs(quotesThirdLineRef) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
        Text("羡长江之无穷。", modifier = Modifier.constrainAs(quotesForthLineRef) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
    }

}
