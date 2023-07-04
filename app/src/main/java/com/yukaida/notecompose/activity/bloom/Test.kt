package com.yukaida.notecompose.activity.bloom

import android.os.Parcelable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.parcelize.Parcelize


@Parcelize
data class Man(val name: String, val age: Int) : Parcelable

@Preview
@Composable
fun test() {
    var name by remember {
        mutableStateOf(0)
    }
    Text(text = name.toString())
    name++

}
