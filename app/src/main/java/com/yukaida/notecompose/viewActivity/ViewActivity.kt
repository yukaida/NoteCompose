package com.yukaida.notecompose.viewActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import com.yukaida.notecompose.R

class ViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        val composeView = findViewById<ComposeView>(R.id.composeView)
        composeView.setContent {
            Text(text = "ViewActivity", modifier = Modifier)
        }

    }
}