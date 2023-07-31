package com.yukaida.notecompose.test

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

data class Man(var name: String)

private const val TAG = "YKD"

@Composable
fun RenScreen(modifier: Modifier = Modifier) {
    Log.d(TAG, "RenScreen: ")

    var man = Man("Kotlin")

    var textOrigin by remember {
        mutableStateOf("Origin")
    }

    Column {
        Log.d(TAG, "Column: ")

        Text(text = textOrigin, modifier = modifier.clickable {
            man = Man("Kotlin2")
            textOrigin = man.name
            Log.d(TAG, "Text: ${man.name}")

        })
        TestComposable(man)
    }

}

@Composable
fun TestComposable(man: Man) {
    Log.d(TAG, "TestComposable: something heavy")

    Text(man.name)
}

