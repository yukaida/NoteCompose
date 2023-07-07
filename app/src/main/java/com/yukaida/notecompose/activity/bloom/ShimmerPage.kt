package com.yukaida.notecompose.activity.bloom

import android.content.res.Configuration
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


val barHeight = 10.dp
val spacerPadding = 3.dp
val roundedCornerShape = RoundedCornerShape(3.dp)

//----色刷
val shimmerColors = listOf(
    Color.LightGray.copy(alpha = 0.6f),
    Color.LightGray.copy(alpha = 0.2f),
    Color.LightGray.copy(alpha = 0.6f)
)


@Preview(showBackground = true)
@Composable
fun ShimmerItem(
    brush: Brush = Brush.linearGradient(
        listOf(
            Color.LightGray.copy(alpha = 0.6f),
            Color.LightGray.copy(alpha = 0.2f),
            Color.LightGray.copy(alpha = 0.6f),
        )
    )
) {

//    val transition = rememberInfiniteTransition()
//    val translateAnimation = transition.animateFloat(
//        initialValue = 0f,
//        targetValue = 1000f,
//        animationSpec = infiniteRepeatable(
//            animation = tween(
//                durationMillis = 1000,
//                easing = FastOutSlowInEasing
//            ), repeatMode = RepeatMode.Reverse
//        )
//    )
//    val brush = Brush.linearGradient(
//        colors = shimmerColors,
//        start = Offset.Zero,
//        end = Offset(x = translateAnimation.value, y = translateAnimation.value)
//    )


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(verticalArrangement = Arrangement.Center) {
                repeat(5) {
                    Spacer(modifier = Modifier.padding(spacerPadding))

                    Spacer(
                        modifier = Modifier
                            .height(barHeight)
                            .clip(roundedCornerShape)
                            .fillMaxWidth(0.7f)
                            .background(brush)
                    )

                    Spacer(modifier = Modifier.padding(spacerPadding))

                }

            }

            Spacer(modifier = Modifier.width(10.dp))

            Spacer(
                modifier = Modifier
                    .size(100.dp)
                    .clip(roundedCornerShape)
                    .background(brush)
            )

        }

        repeat(3) {
            Spacer(modifier = Modifier.padding(spacerPadding))

            Spacer(
                modifier = Modifier
                    .height(barHeight)
                    .clip(roundedCornerShape)
                    .fillMaxWidth()
                    .background(brush)
            )
            Spacer(modifier = Modifier.padding(spacerPadding))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ListPreview() {
    Column(Modifier.padding(5.dp)) {
        repeat(3) {
            ShimmerItem()
        }
    }

}


@Preview(showBackground = true)
@Composable
fun AnimatedShimmerItem() {
    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    ShimmerItem(brush = brush)
}
