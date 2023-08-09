package com.yukaida.notecompose.test.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationEndReason
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun BoundsScreen(){
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    val boxSize = 120.dp

    var paddingTopAnim by remember { mutableStateOf(Animatable(0.dp, Dp.VectorConverter)) }
    var paddingStartAnim by remember { mutableStateOf(Animatable(0.dp, Dp.VectorConverter)) }
    val decay = remember { exponentialDecay<Dp>(absVelocityThreshold = 1f) }

    paddingTopAnim.updateBounds(lowerBound = 0.dp, upperBound = screenHeight - boxSize)
    paddingStartAnim.updateBounds(lowerBound = 0.dp, upperBound = screenWidth - boxSize)

    LaunchedEffect(Unit) {
        delay(800)
        var resultTop = paddingTopAnim.animateDecay(8000.dp, decay)
        while (resultTop.endReason == AnimationEndReason.BoundReached) {
            resultTop = paddingTopAnim.animateDecay(
                -resultTop.endState.velocity,
                decay
            )
        }
    }

    LaunchedEffect(Unit) {
        delay(800)
        var resultStart = paddingStartAnim.animateDecay(8000.dp, decay)
        while (resultStart.endReason == AnimationEndReason.BoundReached) {
            resultStart = paddingStartAnim.animateDecay(
                -resultStart.endState.velocity,
                decay
            )
        }
    }

    Text(text = "screenHeight $screenHeight  screenWidth $screenWidth \n boxSize $boxSize \n top ${paddingTopAnim.value} start ${paddingStartAnim.value}")

    Box(
        modifier = Modifier
            .padding(
                top = paddingTopAnim.value,
                start = paddingStartAnim.value,
                bottom = 0.dp,
                end = 0.dp
            )
            .size(boxSize)
            .background(Color.Blue, RoundedCornerShape(16.dp))
    )

}