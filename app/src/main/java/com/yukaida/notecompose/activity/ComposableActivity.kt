package com.yukaida.notecompose.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yukaida.notecompose.R
import com.yukaida.notecompose.activity.ui.theme.NoteComposeTheme
import com.yukaida.notecompose.activity.ui.theme.horizontalGradientBrush
import com.yukaida.notecompose.activity.ui.theme.verticalGradientBrush

@Preview(showBackground = true)
@Composable
fun PreviewMainLayout() {
    MainLayout()
}

class ComposableActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.onBackground
                ) {
                    MainLayout()
                }
            }
        }
    }
}

@Composable
fun MainLayout() {
    Column {
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

    }


}
