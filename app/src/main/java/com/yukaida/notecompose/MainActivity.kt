package com.yukaida.notecompose

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.yukaida.notecompose.ui.theme.NoteComposeTheme
import com.yukaida.notecompose.viewActivity.ViewActivity
import kotlinx.coroutines.launch

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val mContext = LocalContext.current
    var textInput by remember { mutableStateOf("123") }

    var isTextExpend by remember {
        mutableStateOf(true)
    }
//
//    Column() {
//        TextSample()
//    }
//
//    return

    Column {
        AnimatedVisibility(visible = isTextExpend) {
            Text(
                text = "一段文本",
                Modifier
                    .padding(16.dp)
                    .background(Color.Red, RoundedCornerShape(8.dp))
                    .padding(8.dp)
                    .clickable {
                        isTextExpend = !isTextExpend
                    },
                style = TextStyle(Color.Green)
            )
        }

//        TextSample()

//        ConsLayout()
//
//        Divider(
//            color = Color.Red, modifier =
//            Modifier
//                .fillMaxWidth(1f)
//                .height(8.dp)
//
//        )
//        LazyList()

        Row(modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f))
            .clickable {

            }) {
            Surface(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(50.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.size(8.dp))
            Column(
                Modifier
                    .fillMaxWidth()
                    .height(50.dp), horizontalAlignment = Alignment.Start
            ) {
                Text(text = "标题", fontSize = 16.sp)
                Text(text = "内容内容内容内容内容", fontSize = 8.sp)
            }
        }


        Column(modifier = Modifier.clickable { isTextExpend = !isTextExpend }) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .animateContentSize { initialValue, targetValue -> }
                    .padding(8.dp),
                shadowElevation = 1.dp,

                ) {
                Text(
                    text = "这是一大段测试文本,这是一大段测试文本,这是一大段测试文本,这是一大段测试文本,这是一大段测试文本,这是一大段测试文本,这是一大段测试文本",
                    maxLines = if (isTextExpend) 10 else 1,
                    modifier = Modifier.padding(8.dp),
                )
            }
        }




        TextField(value = textInput, onValueChange = {
            textInput = it
        }, label = { Text(text = "标题") })

        Text(
            text = "Hello $name!", modifier = modifier
        )
        Text(text = "-------------------------------", color = Color.Blue, modifier = modifier)
        Button(onClick = {
            var intent = Intent(mContext, ViewActivity::class.java)
            mContext.startActivity(intent)
        }) {
            Text(text = "TestContent")
        }

//        AndroidView(
//            factory = { context: Context ->
//                WebView(context).apply {
////                settings.javaScriptEnabled = true
//                    webViewClient = WebViewClient()
//                    loadUrl("https://www.baidu.com")
//                }
//            }, modifier = Modifier
//                .fillMaxWidth()
//                .height(200.dp)
//        )

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.background)
        )

        Spacer(
            modifier = Modifier
                .height(20.dp)
                .fillMaxWidth()
                .background(Color.Black, RectangleShape)
        )

        Row() {
            Box(
                Modifier
                    .size(50.dp)
                    .background(Color.Red)
            ) {
                Text(
                    text = "纯色", modifier = Modifier
                        .align(Alignment.Center)
//                        .padding(1.dp)
                        .border(
                            1.dp, Color.Gray, RoundedCornerShape(16.dp)
                        )
                        .padding(8.dp), color = Color.White
                )
            }

            Spacer(
                modifier = Modifier.width(50.dp)
            )

            Box(
                Modifier
                    .size(200.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            mutableListOf(
                                Color.Red, Color.Blue
                            )
                        )
                    )
            ) {
                Text(
                    text = "渐变色",
                    Modifier
                        .align(Alignment.Center)
                        .offset(50.dp, 10.dp)
                        .matchParentSize()
                        .border(
                            1.dp, Color.Gray, RectangleShape
                        ),
                    color = Color.White
                )
            }

            Column(
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
            ) {
                Text(
                    text = "text111", modifier = Modifier
                        .weight(1f)
                        .background(Color.Red)
                )
                Text(
                    text = "text222", modifier = Modifier
                        .weight(1f)
                        .background(Color.Green)
                )
                Text(
                    text = "text333", modifier = Modifier
                        .weight(1f)
                        .background(Color.Yellow)
                )

                Text(
                    text = "textContentTextContentTextContent",
                    maxLines = 1,
                    color = Color.Red,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis
                )

                Text(text = buildAnnotatedString {
                    withStyle(SpanStyle(fontSize = 24.sp)) {
                        append("你现在学习的章节是")
                    }
                    withStyle(SpanStyle(fontSize = 16.sp)) {
                        append("Text")
                    }
                    withStyle(
                        SpanStyle(
                            fontSize = 16.sp,
                            textDecoration = TextDecoration.Underline,
                            color = Color.Red
                        )
                    ) {
                        append("AnnotateString")
                    }

                })
            }


        }


        val annotatedText = buildAnnotatedString {
            withStyle(style = ParagraphStyle(lineHeight = 25.sp)) {
                pushStringAnnotation("URL", "https://www.baidu.com")
            }
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.W900,
                    textDecoration = TextDecoration.Underline,
                    color = Color.Green
                )
            ) {
                append("AnnotatedStringTextText")
            }

        }

        Text(text = "-------------------------------", color = Color.Blue, modifier = modifier)

        ClickableText(text = annotatedText, onClick = { offset ->

            Log.d(TAG, "Greeting: onClick offset $offset")
            Log.d(TAG, "Greeting: onClick annotatedText $annotatedText")

            annotatedText.getStringAnnotations(tag = "URl", start = offset, end = offset)
                .firstOrNull()?.let { annotation ->
                    Log.d(TAG, "Greeting: ${annotation.item}")
                }
        })

        SelectionContainer {
            Text(text = "text can be select")
        }

    }

}

@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_4_XL)
@Composable
fun GreetingPreview() {
    NoteComposeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            Greeting("Android")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_4_XL)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayoutStudy() {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "LayoutStudy")
        }, actions = {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
            }
        })
    }) { innerPadding ->
        BodyContent(Modifier.padding(innerPadding))
    }
}

@Composable
fun BodyContent(modifier: Modifier) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .padding(8.dp)
            .verticalScroll(scrollState, true)
    ) {
        Text(text = "Hi there")
        Text(text = "Thanks for going through the LayoutStudy")
        repeat(50) {
            Text(text = it.toString())
        }
    }
}

@Composable
fun LazyList() {
    val scrollState = rememberLazyListState()

    val scrollStateChange = rememberLazyListState()

    val coroutineScope = rememberCoroutineScope()



    Column {


        Column() {
            Row {
                Button(onClick = { coroutineScope.launch { scrollStateChange.animateScrollToItem(0) } }) {
                    Text(text = "ScrollToTop")
                }
                Button(onClick = { }) {
                    Text(text = "ScrollToEnd")
                }
            }
            LazyColumn(state = scrollStateChange) {
                items(100) {
                    Row(modifier = Modifier.padding(8.dp)) {
                        AsyncImage(
                            model = "https://th.bing.com/th/id/OIP.7KW5GT7NQ8yUGlBbCHEm0gHaNK?pid=ImgDet&rs=1",
                            contentDescription = null,
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(48.dp),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.size(8.dp))

                        Text(text = "Item $it", style = MaterialTheme.typography.titleMedium)
                    }
                }
            }


        }

        Spacer(
            modifier = Modifier
                .padding(8.dp)
                .background(Color.Red)
        )


        LazyColumn(state = scrollState) {
            items(100) {
                Text(text = "Item $it", style = MaterialTheme.typography.titleMedium)
            }
        }

    }

}

@Composable
fun ConsLayout() {
    ConstraintLayout {
        //创建约束
        val (buttonA, textA) = createRefs()

        //button
        Button(onClick = {}, modifier = Modifier
            .constrainAs(buttonA) {
                top.linkTo(parent.top)
                centerHorizontallyTo(parent)
            }
            .padding(16.dp)) {
            Text(text = "Button")
        }

        //text
        Text(text = "测试文本", modifier = Modifier.constrainAs(textA) {
            start.linkTo(buttonA.start)
            end.linkTo(buttonA.end)
            top.linkTo(buttonA.bottom, 16.dp)
        })

        //-----------------
//        val guidelineRef = createGuidelineFromStart(0.5f)
//
//        val textContent = createRef()
//        Text(
//            text = "this is a very very very very very very long content",
//            Modifier.constrainAs(textContent) {
//                linkTo(start = guidelineRef, end = parent.end)
//                top.linkTo(textA.bottom)
//
//                width = Dimension.preferredWrapContent
//            })


    }
}

@Composable
fun TextSample() {
    Text(
        text = "Hello World!",
        modifier = Modifier
            .background(Color.Red)
            .padding(8.dp),
        style = TextStyle(background = Color.Green)
    )
}
