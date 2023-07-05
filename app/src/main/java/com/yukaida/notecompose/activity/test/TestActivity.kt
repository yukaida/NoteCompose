package com.yukaida.notecompose.activity.test

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.drawable.DrawableWrapper
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.UiMode
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yukaida.notecompose.R
import com.yukaida.notecompose.activity.test.ui.theme.NoteComposeTheme

private const val TAG = "TestActivity"

class TestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val vm: TextViewModel = viewModel()
                    CounterScreen(vm)
                    backPressHandler {
                        Log.d(TAG, "backPressHandler")
                    }
                    LaunchedEffect(key1 = "1", block = {
                        Log.d(TAG, "onCreate: ${Thread.currentThread().name}")
                    })
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CounterScreen(vm: TextViewModel, modifier: Modifier = Modifier) {
    var context = LocalContext.current
    var imageVisible by remember {
        mutableStateOf(true)
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {

            AnimatedContent(targetState = vm.counter.value) {
                Text(
                    text = vm.counter.value.toString(),
                    modifier = Modifier
                        .size(200.dp, 100.dp),
                    textAlign = TextAlign.Center
                )
            }

            Button(modifier = Modifier.size(200.dp, 50.dp), onClick = { vm.increment() }) {
                Text(text = "+")
            }
            Spacer(modifier = Modifier.size(16.dp))
            Button(modifier = Modifier.size(200.dp, 50.dp), onClick = {
                vm.decrement()
                imageVisible = !imageVisible
            }) {
                Text(text = "-")
            }
//            Text(text = "abc", modifier = Modifier.firstBaselineToTop(16.dp))
//            CustomLayout(modifier) {
//                Text(text = "abc1", modifier = Modifier.firstBaselineToTop(16.dp))
//                Text(text = "abc2", modifier = Modifier.firstBaselineToTop(16.dp))
//                Text(text = "abc3", modifier = Modifier.firstBaselineToTop(16.dp))
//            }

            Text(text = "---", modifier = Modifier)

            Row(modifier = Modifier.height(IntrinsicSize.Min)) {
                Text(text = "start")
                Divider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(8.dp)
                )
                Text(text = "end")
            }
            Spacer(modifier = Modifier.size(16.dp))
            LoadingProgressBar()
            Spacer(modifier = Modifier.size(16.dp))
            DemoColor()
            Spacer(modifier = Modifier.size(16.dp))
            AnimatedVisibility(visible = imageVisible, enter = fadeIn() + slideInHorizontally()) {
                Box(modifier = Modifier
                    .size(100.dp)
                    .drawWithCache {
                        val imageCat = ImageBitmap.imageResource(
                            context.resources, R.drawable.cat_square
                        )
                        onDrawBehind { }
                        onDrawWithContent {
                            drawContext
                            drawImage(image = imageCat, alpha = 0.5f)
                        }
                    })
            }
            Spacer(modifier = Modifier.size(16.dp))

        }
    }


}

@Preview(showBackground = true)
@Composable
fun CounterScreenPreview() {
    NoteComposeTheme {
        val vm: TextViewModel = viewModel()
        CounterScreen(vm)
    }
}

@Composable
fun backPressHandler(enable: Boolean = true, onBackPressed: () -> Unit) {
    val backDispatcher = checkNotNull(LocalOnBackPressedDispatcherOwner.current) {
        "tips"
    }.onBackPressedDispatcher

    val backCallback = remember {
        object : OnBackPressedCallback(enable) {
            override fun handleOnBackPressed() {
                onBackPressed()
            }
        }
    }


    DisposableEffect(backDispatcher) {
        backDispatcher.addCallback(backCallback)
        onDispose {
            backCallback.remove()
        }
    }

}

class TextViewModel : ViewModel() {
    private val _counter = mutableStateOf(0)

    val counter: State<Int>
        get() = _counter

    fun increment() {
        _counter.value++
    }

    fun decrement() {
        if (_counter.value > 1) {
            _counter.value--
        }
    }


}

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.firstBaselineToTop(firstBaseLineToTop: Dp) =
    Modifier.layout { measurable, constraints ->
        //使用约束进行测量,获得测量结果
        val placeable = measurable.measure(constraints)
        //检查是否存在内容基线
        check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
        //获取内容基线高度
        val firstBaseline = placeable[FirstBaseline]
        //Y坐标
        val placeableY = firstBaseLineToTop.roundToPx() - firstBaseline
        //高度
        val height = placeable.height + placeableY

        //布局
        layout(placeable.width, height) {
            placeable.placeRelative(0, height)
        }

    }

@Composable
fun CustomLayout(modifier: Modifier, content: (@Composable () -> Unit)) {
    Layout(modifier = Modifier, content = content) { measurables, constraints ->
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }

        var yPosition = 0

        layout(constraints.maxWidth, constraints.maxHeight) {
            placeables.forEach { placeable ->
                placeable.placeRelative(x = 0, y = yPosition)
                yPosition += placeable.height
            }

        }


    }
}

@Composable
fun LoadingProgressBar() {
    var progressAngle by remember {
        mutableStateOf(90F)
    }

    Box(modifier = Modifier.size(200.dp), contentAlignment = Alignment.Center) {
        Column {
            Text(text = "loading", Modifier.clickable {
                if (progressAngle < 360) {
                    progressAngle += 30f
                }
            })
            Text(text = "${progressAngle / 360 * 100}%")
        }

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .drawBehind {
//                    drawRoundRect(color = Color.Green)
                }
                .drawWithContent {
                    drawContent()
                }
        ) {
            drawCircle(color = Color.Black, style = Stroke(20.dp.toPx()))
            drawArc(
                color = Color.Red,
                style = Stroke(20.dp.toPx(), cap = StrokeCap.Round),
                startAngle = -90f,
                sweepAngle = progressAngle,
                useCenter = false
            )
        }
    }
}

//@Composable
//fun FlowProgressBar() {
//    val imageBitmapCat = ImageBitmap.imageResource(id = R.drawable.cat_square)
//
//    Canvas(modifier = Modifier.size(100.dp)) {
//        drawImage(imageBitmapCat, colorFilter = kotlin.run {
//            val cm = ColorMatrix().apply { setToSaturation(0f) }
//            ColorFilter.colorMatrix(cm)
//        })
//    }
//
//    Canvas(modifier = Modifier) {
//        drawPath(
//            path = buildWavePath(
//
//            ), brush = ShaderBrush(ImageShader(imageBitmapCat)),
//            alpha = 0.5f
//        )
//    }
//}
//
//fun buildWavePath(
//    width:Float,
//    height:Float,
//    amplitude:Float,
//    progress:Float,
//):Path{
//
//}

@Composable
fun DemoColor() {
    var change by remember { mutableStateOf(false) }
    var flag by remember { mutableStateOf(false) }

    val buttonSize by animateDpAsState(targetValue = if (change) 32.dp else 24.dp)
    val buttonColor by animateColorAsState(
        targetValue = if (change) Color.Red else Color.Gray,
//        animationSpec = spring(
//            Spring.StiffnessHigh
//        )
    )

//    if (buttonSize == 32.dp) {
//        change = false
//    }
    IconButton(onClick = {
        change = !change
        flag = !flag
    }) {
        Log.d(TAG, "DemoColor: $buttonColor")
        Icon(
            Icons.Rounded.Favorite,
            contentDescription = null,
            modifier = Modifier.size(buttonSize),
            tint = buttonColor
        )
    }

}


