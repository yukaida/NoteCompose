package com.yukaida.notecompose.activity.bloom

import android.graphics.Outline
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yukaida.notecompose.activity.ui.theme.bloomShapes
import com.yukaida.notecompose.activity.ui.theme.body2
import com.yukaida.notecompose.activity.ui.theme.h1
import com.yukaida.notecompose.activity.ui.theme.textButtonStyle
import com.yukaida.notecompose.ui.theme.gray
import com.yukaida.notecompose.ui.theme.pink900
import com.yukaida.notecompose.ui.theme.white

@Preview
@Composable
fun LoginPagePreview() {
    LoginPage()
}

@Composable
fun LoginPage() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .background(white)
    ) {
        Text(
            text = "Log in with email", style = h1, color = gray,
            modifier = Modifier
                .fillMaxWidth()
                .paddingFromBaseline(top = 184.dp, bottom = 16.dp), textAlign = TextAlign.Center
        )
        LoginInputBox()
        HintWithUnderLine()
        LoginButton()
    }
}

@Composable
fun LoginInputBox() {
    LoginTextField("Email address")
    Spacer(modifier = Modifier.height(8.dp))
    LoginTextField("Password (8+characters)")

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTextField(placeHolder: String) {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        placeholder = { Text(text = placeHolder) },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(bloomShapes.small)
    )
}

@Composable
fun HintWithUnderLine() {
    Column(modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 16.dp)) {
        TopText()
        BottomText()
    }
}

@Composable
fun TopText() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        var keywordPre = "By Clicking below you agree to our".split(" ")
        var keywordPost = "and consent".split(" ")
        for (word in keywordPre) {
            Text(text = word, style = body2, color = gray)
        }

        Text(text = "Terms of Use", style = body2, textDecoration = TextDecoration.Underline)

        for (word in keywordPost) {
            Text(text = word, style = body2, color = gray)
        }
    }
}

@Composable
fun BottomText() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(text = "to Our", style = body2, color = gray)
        Text(
            text = "Privacy Policy.",
            style = body2,
            color = gray,
            textDecoration = TextDecoration.Underline
        )
    }
}

@Composable
fun LoginButton() {
    Button(
        onClick = {

        },
        Modifier
            .fillMaxWidth()
            .clip(bloomShapes.medium)
            .padding(top = 16.dp),
        colors = ButtonDefaults.buttonColors(contentColor = pink900)
    ) {
        Text(text = "Log in", style = textButtonStyle, color = white)
    }
}