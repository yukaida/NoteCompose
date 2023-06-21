package com.yukaida.notecompose.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.platform.textInputServiceFactory
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.yukaida.notecompose.R
import com.yukaida.notecompose.activity.ui.theme.NoteComposeTheme
import com.yukaida.notecompose.activity.ui.theme.horizontalGradientBrush
import com.yukaida.notecompose.activity.ui.theme.verticalGradientBrush
import org.w3c.dom.Text

@Preview(showBackground = true)
@Composable
fun PreviewMainLayout() {
    MainLayout()
}

private val TAG = "ComposableActivity"

class ComposableActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.onBackground
                ) {
                    Column() {
                        MainLayout()
                    }
                }
            }
        }
    }
}

@Composable
fun MainLayout() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
            .verticalScroll(rememberScrollState())
    ) {
        Column() {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .wrapContentHeight(align = Alignment.Top)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cat_square),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    painter = painterResource(id = R.drawable.cat_square),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
            }

            Row() {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color.Red)
                ) {
                    Text(text = "纯色", Modifier.align(Alignment.Center))
                }
                Spacer(modifier = Modifier.size(16.dp))
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(verticalGradientBrush)
                ) {
                    Text(text = "渐变色 垂直", Modifier.align(Alignment.Center))
                }
                Spacer(modifier = Modifier.size(16.dp))
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(horizontalGradientBrush)
                ) {
                    Text(text = "渐变色 水平", Modifier.align(Alignment.Center))
                }
            }

            Box(
                modifier = Modifier
//                    .offset(16.dp, 16.dp)
//                    .offset { IntOffset(32.dp.roundToPx(), 32.dp.roundToPx()) }
                    .padding(8.dp)
                    .border(8.dp, Color.Red, RoundedCornerShape(2.dp))
                    .padding(16.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color.Green)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .background(Color.LightGray)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .background(Color.White)
                )
                Box(
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxWidth()
                        .background(Color.Yellow)
                ) {
                    Text(
                        text = "matchParentSize",
                        modifier = Modifier
                            .padding(8.dp)
                            .border(
                                8.dp, verticalGradientBrush, RoundedCornerShape(8.dp)
                            )
                            .padding(16.dp)
                            .matchParentSize()
                            .align(Alignment.Center),
                        textAlign = TextAlign.Center
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .background(Color.Cyan)
                ) {
                    Text(
                        text = stringResource(id = R.string.test_content), style = TextStyle(
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            background = Color.Cyan,
                            lineHeight = 35.sp,
                            letterSpacing = 4.sp,
                            textDecoration = TextDecoration.LineThrough
                        ), modifier = Modifier.align(Alignment.TopCenter)
                    )

                    Text(
                        text = stringResource(id = R.string.test_content),
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 8.sp),
                        modifier = Modifier.align(Alignment.BottomCenter),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontFamily = FontFamily.Serif
                    )

                }

                AnnotatedStringText()
                BaiduClickText()
                SelectionContainer {
                    Text(text = "这是一串可以复制的文本")
                }

            }

            var sliderValue by remember {
                mutableStateOf(0f)
            }

            Column {
                MenuSample()
                ChainSample()
                BarrierSample()
                ConstraintLayoutSample()
                SurfaceSample()
                CardTextSample()
                ProgressSample()
                AlertDialogSample()
                DialogSample()
                Slider(value = sliderValue, onValueChange = {
                    sliderValue = it
                }, steps = 4)
                ExFAB()
                InteractionSourceDemo()
                NameInputTextField()
                Spacer(modifier = Modifier.size(8.dp))
                BasicTextFieldSample()
                IconSample()
                ButtonSample()
            }
        }
    }
}

@Composable
fun MenuSample() {
    val menuExpanded by remember {
        mutableStateOf(false)
    }
//    KSurface {
    Menu(options = listOf("1", "2", "3"), expanded = menuExpanded, {})
//    }
}

@Composable
fun Menu(options: List<String>, expanded: Boolean, onDismissRequest: () -> Unit) {
    DropdownMenu(expanded = expanded, onDismissRequest = { onDismissRequest }) {
//        Column() {
        options.forEach { option ->
            Text(text = option, modifier = Modifier
                .padding(16.dp)
                .clickable {
//                    expanded = false
                })

//            }
        }
    }
}

@Composable
fun ChainSample() {
    KSurface {
        ConstraintLayout {
            val (a, b, c, d) = createRefs()
            createVerticalChain(a, b, c, d, chainStyle = ChainStyle.Spread)
            Text(text = "   a   ", modifier = Modifier.constrainAs(a) {})
            Text(text = "   b   ", modifier = Modifier.constrainAs(b) {})
            Text(text = "   c   ", modifier = Modifier.constrainAs(c) {})
            Text(text = "   d   ", modifier = Modifier.constrainAs(d) {})
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarrierSample() {
    var name by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
//    var barrier =
//    KSurface {
    ConstraintLayout(modifier = Modifier.wrapContentSize()) {
        val (textName, textPassword, textFieldName, textFieldPassword) = createRefs()
        val textEndBarrier = createEndBarrier(textName, textPassword)

        Text(text = "用户名", modifier = Modifier.constrainAs(textName) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
        })
        Text(text = "密码", modifier = Modifier.constrainAs(textPassword) {
            top.linkTo(textName.bottom, 64.dp)
            start.linkTo(parent.start)
        })
        TextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier.constrainAs(textFieldName) {
                top.linkTo(parent.top)
                start.linkTo(textEndBarrier, 8.dp)
            })
        TextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.constrainAs(textFieldPassword) {
                top.linkTo(textPassword.top)
                start.linkTo(textEndBarrier, 8.dp)
            })
    }
//    }
}

@Composable
fun KSurface(content: @Composable () -> Unit) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = Color.LightGray, shadowElevation = 10.dp,
        modifier = Modifier
            .padding(16.dp)
            .border(1.dp, Color.Red)
            .padding(8.dp)
    ) {
        content()
    }
}

@Composable
fun ConstraintLayoutSample() {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = Color.LightGray, shadowElevation = 10.dp,
        modifier = Modifier
            .padding(16.dp)
            .border(1.dp, Color.Red)
            .padding(8.dp)
    ) {
        ConstraintLayout(modifier = Modifier.wrapContentSize()) {
            val (imageRef, titleRef, contentRef) = remember {
                createRefs()
            }

            Image(painter = painterResource(id = R.drawable.android_robot),
                contentDescription = null,
                Modifier
                    .size(48.dp)
                    .constrainAs(imageRef) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    })

            Text(text = "Compose 技术爱好者",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.constrainAs(titleRef) {
                    top.linkTo(parent.top)
                    start.linkTo(imageRef.end, 8.dp)
                    end.linkTo(parent.end)
                })

            Text(text = "我的个人描述...",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.constrainAs(contentRef) {
                    top.linkTo(titleRef.bottom, 8.dp)
                    start.linkTo(imageRef.end, 8.dp)
                    end.linkTo(parent.end)
                })

        }
    }

}

@Composable
fun SurfaceSample() {
    Surface(
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 8.dp,
        color = Color.Cyan,
        modifier = Modifier.padding(16.dp)
    ) {
        Row() {
            Image(
                painter = painterResource(id = R.drawable.cat_square),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp, 0.dp, 0.dp, 8.dp))
            )
            Spacer(modifier = Modifier.size(8.dp))
            Column(
                modifier = Modifier
                    .padding(16.dp, 0.dp)
                    .height(64.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(text = "Liratie")
                Text(text = "礼谙")
            }
        }
    }
}

@Composable
fun CardTextSample() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 10.dp
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = "JetPack Compose是什么", style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
fun ProgressSample() {
    var progress by remember {
        mutableStateOf(0.1f)
    }
    val animatedProgress by animateFloatAsState(
        targetValue = progress, animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )

    Column {
        CircularProgressIndicator(progress = animatedProgress)
        OutlinedButton(onClick = {
            if (progress < 1f) {
                progress += 0.1f
            }
        }) {
            Text("add")
        }
    }

}

@Composable
fun AlertDialogSample() {
    val openDialog = remember {
        mutableStateOf(false)
    }
    if (openDialog.value) {
        AlertDialog(title = { Text(text = "开启位置服务") },
            text = { Text(text = "这将意味着,我们会给您提供精准的位置服务,并且您将接收关于您订阅的位置信息") },
            onDismissRequest = { openDialog.value = false },
            confirmButton = {
                Button(onClick = {
                    openDialog.value = false
                }) {
                    Text(text = "确定")
                }
            },
            dismissButton = {
                Button(onClick = {
                    openDialog.value = false
                }) {
                    Text(text = "取消")
                }
            })
    }
}

@Composable
fun DialogSample() {
    var showState by remember {
        mutableStateOf(false)
    }
    if (showState) {
        Dialog(
            onDismissRequest = { showState = false }, properties = DialogProperties(
                true, true, usePlatformDefaultWidth = false, decorFitsSystemWindows = true
            )
        ) {
            Surface(
                modifier = Modifier
                    .width(300.dp)
                    .height(200.dp)
            ) {
                Text(text = "this is a dialog")
            }
        }
    }

}

@Composable
fun ExFAB() {
    ExtendedFloatingActionButton(onClick = { /*TODO*/ }) {
        Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
        Text(text = "ExtendFAB")
    }
}

@Composable
fun InteractionSourceDemo() {
    var interactionSource = remember {
        MutableInteractionSource()
    }
    val pressState = interactionSource.collectIsPressedAsState()
    Button(onClick = { }, interactionSource = interactionSource) {
        Text(text = if (pressState.value) "按下" else "离开")
    }
}

@Composable
fun AnnotatedStringText() {
    Text(text = buildAnnotatedString {
        withStyle(style = SpanStyle(fontSize = 24.sp)) {
            append("你现在学习的章节是")
        }
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.W900, fontSize = 24.sp, color = Color.Red
            )
        ) {
            append("Text")
        }
        append("\n")

        withStyle(style = ParagraphStyle(lineHeight = 24.sp)) {
            append("在刚刚讲过的内容中,我们学会了如何应用文字样式,以及如何限制文本的行数和处理溢出的视觉效果.")
            append("\n")
            append("现在,我们正在学习")

            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.W900,
                    textDecoration = TextDecoration.Underline,
                    color = Color.Red
                )
            ) {
                append("AnnotatedString")
            }
        }


    })
}

val annotatedTobeClickText = buildAnnotatedString {
    withStyle(style = ParagraphStyle(lineHeight = 24.sp)) {
        pushStringAnnotation(tag = "URL", annotation = "https://www.baidu.com")
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.W900,
                textDecoration = TextDecoration.Underline,
                color = Color.Blue
            )
        ) {
            append("baidu官网")
        }
        pop()
    }

}

@Composable
fun BaiduClickText() {
    ClickableText(text = annotatedTobeClickText, onClick = { offset ->
        Log.d(TAG, "AnnotatedStringText offset:$offset ")
        annotatedTobeClickText.getStringAnnotations(
            tag = "URL", start = offset, end = offset
        ).firstOrNull()?.let { annotation ->
            Log.d(TAG, "AnnotatedStringText annotation.item: ${annotation.item}")
        }
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameInputTextField() {
    var nameInput by remember {
        mutableStateOf("")
    }
    var isNameInputError by remember {
        mutableStateOf(true)
    }

    OutlinedTextField(value = nameInput, onValueChange = {
        nameInput = it
        isNameInputError = it != "余凯达"
    }, isError = isNameInputError, label = { Text(text = "name") }, leadingIcon = {
        Image(
            painter = painterResource(id = R.drawable.cat_square),
            contentDescription = null,
            modifier = Modifier
                .size(32.dp)
                .clip(RoundedCornerShape(8.dp))
        )
    }, trailingIcon = {
        IconButton(
            onClick = {
                Log.d(TAG, "trailingIcon onClicked ")
            }, modifier = Modifier.size(32.dp)

        ) {
            Image(
                painter = painterResource(id = R.drawable.android_robot),
                contentDescription = null,
                modifier = Modifier.clip(CircleShape)
            )
        }
    }

    )
}

@Composable
fun BasicTextFieldSample() {
    var text by remember {
        mutableStateOf("")
    }

    BasicTextField(modifier = Modifier.width(300.dp),
        value = text,
        onValueChange = { text = it },
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier.background(Color.White, RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.android_robot),
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                    )
                    innerTextField()
                    IconButton(onClick = { text = "" }) {
                        Icon(imageVector = Icons.Filled.Close, contentDescription = null)
                    }
                }

                if (text.isEmpty()) {
                    Text(text = "输入点东西看看吧")
                }
            }
        })

}

@Composable
fun IconSample() {
    Icon(imageVector = Icons.Filled.Favorite, contentDescription = null, tint = Color.Red)
}

@Composable
fun ButtonSample() {
    Button(onClick = { /*TODO*/ }) {
        Icon(
            imageVector = Icons.Filled.Done,
            contentDescription = null,
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        Text(text = "确认")
    }
}