package com.yukaida.notecompose.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.lifecycleScope
import com.yukaida.notecompose.viewActivity.ui.theme.NoteComposeTheme
import kotlinx.coroutines.launch


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ListLayoutPre() {
    ListLayout(ListViewModel())
}

class ListActivity : ComponentActivity() {
    private val vm by viewModels<ListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ListLayout(vm)
                }
            }
        }
    }
}

@Composable
fun ListLayout(
    vm: ListViewModel
) {
    ConstraintLayout {
        val name = vm.name.observeAsState().value.toString()
        val intList = vm.intList.observeAsState().value

        val (lazyColumnRef, buttonRef, textRef) = createRefs()

        Text(text = name, Modifier.constrainAs(textRef) {
            top.linkTo(parent.top, 16.dp)
            centerHorizontallyTo(parent)
        })


        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .constrainAs(lazyColumnRef) {
                    top.linkTo(textRef.bottom)
                    centerHorizontallyTo(parent)
                }
                .fillMaxWidth()
                .background(Color.Gray, RoundedCornerShape(8.dp))
                .padding(16.dp),
            content = {
                if (intList != null) {
                    items(intList.size) {
                        val content = "item ${intList[it]}"

                        Text(
                            text = content,
                            Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                                .background(Color.Blue, RoundedCornerShape(8.dp))
                                .padding(8.dp)
                                .clickable {
                                    vm.changeName(content)
                                },
                            style = TextStyle(Color.White),
                            textAlign = TextAlign.Center
                        )
                    }
                }

            })

        Button(onClick = {
            vm.changeName((0..100).random().toString())
            vm.refreshNameList()
        }, modifier = Modifier
            .constrainAs(buttonRef) {
                bottom.linkTo(parent.bottom)
                centerHorizontallyTo(parent)
            }) {
            Text(
                text = "修改"
            )
        }


    }
}
