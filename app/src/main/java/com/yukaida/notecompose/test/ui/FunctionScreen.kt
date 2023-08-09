package com.yukaida.notecompose.test.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun TransitionScreen() {
    var big by remember { mutableStateOf(false) }
    val boxSize = animateDpAsState(
        if (big) 120.dp else 40.dp,
        label = "boxAnimate",
        animationSpec = tween(1500)
    )

    val bigTransition = updateTransition(targetState = big, label = "bigStateTransition")
    val bigSizeAnimatableState =
        bigTransition.animateDp(
            transitionSpec = {

                when {
                    false isTransitioningTo true -> tween(3000)
                    else -> spring()
                }

            },
            label = "size"
        ) { state ->

            if (state) 120.dp else 40.dp
        }

    Box(modifier = Modifier
        .size(bigSizeAnimatableState.value)
//        .size(boxSize.value)
        .background(Color.Blue, RoundedCornerShape(8.dp))
        .clickable {
            big = !big
        })
}

@Composable
fun AnimatedVisibilityScreen() {
    var show by remember { mutableStateOf(false) }
    Column {
        AnimatedVisibility(
            visible = show,
            enter = expandIn(
                tween(3000),
                expandFrom = Alignment.BottomEnd,
                clip = true,
                initialSize = { IntSize(0, 0) })
                    + fadeIn(tween(3000)), exit = shrinkOut()
        ) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(Color.Blue, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "A", color = Color.White)
            }
        }
        Text(text = "showOrHide", modifier = Modifier.clickable {
            show = !show
        })
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CrossFadeScreen() {
    var show by remember { mutableStateOf(false) }

    var count by remember { mutableStateOf(0) }
    Column {
        AnimatedContent(
            targetState = count,
            transitionSpec = {
                // Compare the incoming number with the previous number.
                if (targetState > initialState) {
                    // If the target number is larger, it slides up and fades in
                    // while the initial (smaller) number slides up and fades out.
                    (slideInVertically { height -> height } + fadeIn()).togetherWith(
                        slideOutVertically { height -> -height } + fadeOut())
                } else {
                    // If the target number is smaller, it slides down and fades in
                    // while the initial number slides down and fades out.
                    (slideInVertically { height -> -height } + fadeIn()).togetherWith(
                        slideOutVertically { height -> height } + fadeOut())
                }.using(
                    // Disable clipping since the faded slide-in/out should
                    // be displayed out of bounds.
                    SizeTransform(clip = false)
                )
            }, label = ""
        ) { targetCount ->
            Text(text = "$targetCount")
        }

        Spacer(modifier = Modifier.size(16.dp))


        AnimatedContent(targetState = false, label = "") {
            if (it) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .background(Color.Blue, RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "A", color = Color.White)
                }
            } else {
                Text(
                    text = "B",
                    color = Color.Black
                )
            }
        }

        Text(
            text = "showOrHide",
            modifier = Modifier
                .padding(16.dp)
                .size(80.dp)
                .clickable {
                    show = !show
                    count++
                },
            textAlign = TextAlign.Center
        )


    }

}