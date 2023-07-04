package com.yukaida.notecompose.activity.test

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
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

@Composable
fun CounterScreen(vm: TextViewModel, modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Text(
                text = vm.counter.value.toString(),
                modifier = Modifier
                    .size(200.dp, 100.dp),
                textAlign = TextAlign.Center
            )
            Button(modifier = Modifier.size(200.dp, 50.dp), onClick = { vm.increment() }) {
                Text(text = "+")
            }
            Spacer(modifier = Modifier.size(16.dp))
            Button(modifier = Modifier.size(200.dp, 50.dp), onClick = { vm.decrement() }) {
                Text(text = "-")
            }
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