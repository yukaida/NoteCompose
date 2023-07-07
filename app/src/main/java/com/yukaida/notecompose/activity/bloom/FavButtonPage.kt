package com.yukaida.notecompose.activity.bloom

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.yukaida.notecompose.activity.ButtonSample
import com.yukaida.notecompose.activity.test.FavButton
import com.yukaida.notecompose.activity.test.animateDuration
import com.yukaida.notecompose.ui.theme.purple500

//定义UI状态
data class UiState(
    val backgroundColor: Color,
    val textColor: Color,
    val roundedCorner: Int,
    val buttonWidth: Dp
)

//枚举出两种UI状态
enum class ButtonState(val ui: UiState) {
    Idle(UiState(purple500, Color.White, 50, 60.dp)),
    Pressed(UiState(Color.White, purple500, 6, 300.dp))
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimateFavButton(modifier: Modifier = Modifier) {
    var buttonState by remember {
        mutableStateOf(ButtonState.Idle)
    }

    Box(modifier) {
        AnimatedContent(targetState = buttonState, transitionSpec = {
            fadeIn(tween(durationMillis = animateDuration)) with
                    fadeOut(tween(durationMillis = animateDuration)) using
                    SizeTransform { initialSize, targetSize ->
                        tween(durationMillis = animateDuration)
                    }
        }) { state ->
//            Column() {
            AnimatedFavButton2(modifier = modifier)

//                FavButton2(buttonState = buttonState) {
//                    buttonState =
//                        if (buttonState == ButtonState.Idle)
//                            ButtonState.Pressed
//                        else
//                            ButtonState.Idle
//                }

//            }

        }
    }
}


@Composable
fun FavButtonNew(
    modifier: Modifier = Modifier,
    buttonState: ButtonState,
    textColor: Color = buttonState.ui.textColor,
    backgroundColor: Color = buttonState.ui.backgroundColor,
    roundedCorner: Int = buttonState.ui.roundedCorner,
    buttonWidth: Dp = buttonState.ui.buttonWidth,
    onClick: () -> Unit
) {
    Button(
        border = BorderStroke(1.dp, purple500),
        modifier = modifier.size(buttonWidth, height = 60.dp),
        shape = RoundedCornerShape(roundedCorner.coerceIn(0..100)),
        colors = ButtonDefaults.buttonColors(backgroundColor),
        onClick = onClick
    ) {

        if (buttonState == ButtonState.Idle
            && textColor == ButtonState.Idle.ui.textColor
        ) {
            Icon(
                tint = textColor,
                imageVector = Icons.Default.Favorite,
                modifier = Modifier.size(24.dp),
                contentDescription = null
            )
        } else {
            Row {
                Icon(
                    tint = textColor,
                    imageVector = Icons.Default.FavoriteBorder,
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.CenterVertically),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    "ADD TO FAVORITES!",
                    softWrap = false,
                    modifier = Modifier.align(Alignment.CenterVertically),
                    color = textColor
                )
            }
        }
    }
}


@Preview
@Composable
fun FavButtonPv() {
    FavButton(buttonState = ButtonState.Idle) {

    }
}


@Composable
fun FavButton2(
    modifier: Modifier = Modifier,
    buttonState: ButtonState,
    textColor: Color = buttonState.ui.textColor,
    backgroundColor: Color = buttonState.ui.backgroundColor,
    roundedCorner: Int = buttonState.ui.roundedCorner,
    buttonWidth: Dp = buttonState.ui.buttonWidth,
    onClick: () -> Unit
) {
    Button(
        border = BorderStroke(1.dp, purple500),
        modifier = modifier.size(buttonWidth, height = 60.dp),
        shape = RoundedCornerShape(roundedCorner.coerceIn(0..100)),
        colors = ButtonDefaults.buttonColors(backgroundColor),
        onClick = onClick,
    ) {
        if (buttonState == ButtonState.Idle
            && textColor == ButtonState.Idle.ui.textColor
        ) {
            Icon(
                tint = textColor,
                imageVector = Icons.Default.Favorite,
                modifier = Modifier.size(24.dp),
                contentDescription = null
            )
        } else {
            Row {
                Icon(
                    tint = textColor,
                    imageVector = Icons.Default.FavoriteBorder,
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.CenterVertically),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    "ADD TO FAVORITES!",
                    softWrap = false,
                    modifier = Modifier.align(Alignment.CenterVertically),
                    color = textColor
                )
            }
        }
    }
}


@Composable
fun AnimatedFavButton2(modifier: Modifier = Modifier) {
    var buttonState by remember { mutableStateOf(ButtonState.Idle) }

    val transition = updateTransition(targetState = buttonState, label = "")
    val backgroundColor by transition.animateColor(transitionSpec = {
        tween(durationMillis = animateDuration)
    }, label = "") { it.ui.backgroundColor }
    val textColor by transition.animateColor(transitionSpec = {
        tween(durationMillis = animateDuration)
    }, label = "") { it.ui.textColor }
    val roundedCorner by transition.animateInt(transitionSpec = {
        tween(durationMillis = animateDuration)
    }, label = "") { it.ui.roundedCorner }
    val buttonWidth by transition.animateDp(transitionSpec = {
        tween(durationMillis = animateDuration)
    }, label = "") { it.ui.buttonWidth }

    FavButton(
        modifier, buttonState, textColor, backgroundColor, roundedCorner, buttonWidth,
    ) {
        buttonState =
            if (buttonState == ButtonState.Idle) ButtonState.Pressed
            else ButtonState.Idle
    }
}