package com.yukaida.notecompose.test

import android.os.Bundle
import android.util.Log
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationEndReason
import androidx.compose.animation.core.AnimationResult
import androidx.compose.animation.core.AnimationState
import androidx.compose.animation.core.DecayAnimation
import androidx.compose.animation.core.DecayAnimationSpec
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.StartOffsetType
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateDecay
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.defaultDecayAnimationSpec
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.yukaida.notecompose.activity.bloom.roundedCornerShape
import com.yukaida.notecompose.activity.test.animateDuration
import com.yukaida.notecompose.test.ui.AnimatedVisibilityScreen
import com.yukaida.notecompose.test.ui.CrossFadeScreen
import com.yukaida.notecompose.test.ui.TransitionScreen
import com.yukaida.notecompose.test.ui.theme.NoteComposeTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale

@Stable
data class User(var name: String)

class Man(name: String) {
    var name by mutableStateOf(name)
}

private const val TAG = "YKD"

class RenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            //            TransitionScreen()
//            AnimatedVisibilityScreen()
            CrossFadeScreen()
        }

//            Column {


//                var sizeAnimatable = remember {
//                    Animatable(120.dp, Dp.VectorConverter)
//                }
//                sizeAnimatable.updateBounds(lowerBound = 130.dp, upperBound = 300.dp)
//                var textContent by remember { mutableStateOf("test content") }
//
//                LaunchedEffect(Unit) {
//                    delay(2000)
////                    sizeAnimatable.animateTo(320.dp, tween(800)) {
////                        textContent = sizeAnimatable.value.toString()
////                    }
//
//                    val result = sizeAnimatable.animateDecay(1200.dp, exponentialDecay()) {
//                        textContent = sizeAnimatable.value.toString()
//                    }
//
//                    if (result.endReason == AnimationEndReason.BoundReached) {
//                        sizeAnimatable.animateDecay(-result.endState.velocity, exponentialDecay()){
//                            textContent = sizeAnimatable.value.toString()
//                        }
//                    }
//
//                }
//
//
//                Text(
//                    text = textContent,
//                    modifier = Modifier
//                        .background(Color.Blue, RoundedCornerShape(8.dp))
//                        .size(sizeAnimatable.value),
//                    textAlign = TextAlign.Center,
//                    color = Color.White,
//                )

//                LaunchedEffect(Unit) {
//                    delay(2300)
//                    sizeAnimatable.animateTo(120.dp, tween(1200))
//                    sizeAnimatable.stop()
//                }
//
//                LaunchedEffect(Unit) {
//                    delay(2500)
//                    sizeAnimatable.stop()
//                }


//        }
//            var bigSize by remember { mutableStateOf(false) }
//            val boxSize by animateDpAsState(
//                if (bigSize) 120.dp else 40.dp,
//                label = "",
//                animationSpec =
//                tween(300)
//                spring(
//                    dampingRatio = Spring.DampingRatioMediumBouncy,
//                    stiffness = Spring.StiffnessMedium,
//                    1.dp
//                )
//                repeatable(
//                    iterations = 3,
//                    animation = tween(),
//                    repeatMode = RepeatMode.Reverse,
//                    initialStartOffset = StartOffset(600, StartOffsetType.FastForward)
//                )
//                infiniteRepeatable(
//                    animation = tween(),
//                    repeatMode = RepeatMode.Reverse,
//                    initialStartOffset = StartOffset(600, StartOffsetType.FastForward)
//                )
//                keyframes {
//                    if (bigSize)
//                        110.dp at 50 with FastOutSlowInEasing
//                    else
//                        50.dp at 50
//                    durationMillis=2000
//                    delayMillis=100
//                }
//            )

//            val sizeAnimate =
//                remember { Animatable(if (bigSize) 120.dp else 40.dp, Dp.VectorConverter) }

//            LaunchedEffect(bigSize) {
////                sizeAnimate.snapTo(if (bigSize) 140.dp else 20.dp)
////                sizeAnimate.animateTo(
////                    if (bigSize) 120.dp else 40.dp,
//////                    spring(Spring.DampingRatioMediumBouncy)
////                    tween(2000 )
////                )
//
//            }
//
//            Column {
//                Box(modifier = Modifier
//                    .size(boxSize)
//                    .background(color = Color.Blue)
//                    .clickable {
//                        bigSize = !bigSize
//                    })
//                Spacer(modifier = Modifier.size(16.dp))
//                Text(text = boxSize.toString())
//            }


//            Column {
//                val boxSize by animateDpAsState(if (bigSize) 80.dp else 40.dp, label = "")
//
//                Box(modifier = Modifier.size(boxSize).background(Color.RED).clickable {
//                }) {
//                    bigSize = !bigSize
//                }
//
//
//                var user = User("Kotlin")
//                var text = remember {
//                    mutableStateOf("Origin")
//                }
//
//
//                var name by remember {
//                    mutableStateOf("yukaida")
//                }
////                val nameUpCase by remember {
////                    derivedStateOf { name.uppercase(Locale.ROOT) }
////                }
//
//                val nameUpCase = remember(name) {
//                    name.uppercase(Locale.ROOT)
//                }
//
//
//                Text(text = nameUpCase, modifier = Modifier.clickable { name = "kotlin" })
//
//                Log.d(TAG, "Column: ----start")
//
//                Spacer(modifier = Modifier.size(16.dp))
//                RenScreen(textInput = text, user = user)
//                Spacer(modifier = Modifier.size(16.dp))
//                Text(text = "ChangeText", modifier = Modifier.clickable {
//                    text.value = "change by changeText"
////                            user = User("KotlinNew")
//                    user = User("Kotlin")
//                })
////                Heavy(user)
//                Log.d(TAG, "Column: ----end")
//            }
//        }
    }
}


@Composable
fun RenScreen(modifier: Modifier = Modifier, textInput: MutableState<String>, user: User) {
    Log.d(TAG, "RenScreen:${textInput.value} ")
    Column {

        Text(text = textInput.value, modifier = modifier.clickable {
            Log.d(TAG, "Text has been clicked: ${textInput.value}")
            textInput.value = "change"
        })

        Heavy(user)
    }

}

var LocaleUserAge = compositionLocalOf<Int> { error("empty data") }

@Composable
fun Heavy(user: User) {
    Log.d(TAG, "Heavy: 复杂操作")
    Text(user.name)


    CompositionLocalProvider(LocaleUserAge provides 26) {
        Log.d(TAG, "Heavy: ${LocaleUserAge.current}")
    }

}


@Preview(showBackground = true)
@Composable()
fun RenScreenPreview() {

    var user = User("Kotlin")
    var text = remember {
        mutableStateOf("Origin")
    }
    Column {
        Spacer(modifier = Modifier.size(16.dp))
        RenScreen(textInput = text, user = user)
    }
}