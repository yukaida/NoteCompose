package com.yukaida.notecompose.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
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

class ListActivity : ComponentActivity() {
    val vm by viewModels<ListViewModel>()

    private val TAG = "ListActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    vm.addItem("111")
//                    vm.addItem("222")
//                    vm.addItem("333")
//                    vm.addItem("444")
//
//                    Log.d(TAG, "onCreate: ")
//                    vm.name.value?.let { ListLayout(it.toMutableList()) }
                    ListLayout(vm)
                }
            }
        }


//        vm.name.observe(this) {
//
//        }
    }
}

//
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ListLayoutPre() {
//    ListLayout("yukaida")
}

@Composable
fun ListLayout(
//    nameList: MutableList<String>
    vm: ListViewModel
) {
    ConstraintLayout {
        val (lazyColumnRef, buttonRef) = createRefs()

        val name = vm.name.observeAsState()

        Text(text = name.value.toString())
//        LazyColumn(
//            modifier = Modifier
//                .padding(8.dp)
//                .constrainAs(lazyColumnRef) {
//                    top.linkTo(parent.top)
//                    centerHorizontallyTo(parent)
//                }
//                .fillMaxWidth()
//                .background(Color.Gray, RoundedCornerShape(8.dp))
//                .padding(16.dp),
//            content = {
//                items(nameList.size) {
//                    Text(
//                        text = "item ${nameList[it]}",
//                        Modifier
//                            .padding(8.dp)
//                            .fillMaxWidth()
//                            .background(Color.Blue, RoundedCornerShape(8.dp))
//                            .padding(8.dp), style = TextStyle(Color.White),
//                        textAlign = TextAlign.Center
//                    )
//                }
//
//            })

        Button(onClick = {
            vm.changeName((0..100).random().toString())
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
