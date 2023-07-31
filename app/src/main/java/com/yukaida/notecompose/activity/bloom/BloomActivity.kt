package com.yukaida.notecompose.activity.bloom

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
//import androidx.compose.ui.platform.textInputServiceFactory
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yukaida.notecompose.R
import com.yukaida.notecompose.activity.ui.theme.NoteComposeTheme
import com.yukaida.notecompose.activity.ui.theme.bloomShapes
import com.yukaida.notecompose.activity.ui.theme.nunitoSansFamily
import com.yukaida.notecompose.activity.ui.theme.textButtonStyle
import com.yukaida.notecompose.ui.theme.gray
import com.yukaida.notecompose.ui.theme.pink100
import com.yukaida.notecompose.ui.theme.pink900
import com.yukaida.notecompose.ui.theme.white

@Preview(showBackground = true)
@Composable
fun WelcomePreview() {
    NoteComposeTheme {
        AppNavigation()
    }
}

class BloomActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        window.setDecorFitsSystemWindows(true)
        WindowCompat.setDecorFitsSystemWindows(window,true)
        setContent {
            NoteComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun WelcomePage(modifier: Modifier = Modifier, navController: NavController) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(pink100)
    ) {
        Image(
            painter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_light_welcome_bg)),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
//        Image(
//            painter = painterResource(id = R.drawable.ic_light_welcome_bg),
//            contentDescription = null,
//            modifier = Modifier.fillMaxSize()
//        )
        WelcomeContent(navController)
    }
}

@Composable
fun WelcomeContent(navController: NavController) {
    Column {
        Spacer(modifier = Modifier.height(72.dp))
        Image(
            painter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_light_welcome_illos)),
            contentDescription = null,
            modifier = Modifier
                .wrapContentSize()
                .padding(start = 88.dp)
        )
        Spacer(modifier = Modifier.height(48.dp))
        WelcomeTitle()
        Spacer(modifier = Modifier.height(40.dp))
        Button(
            onClick = { navController.navigate("login") {} },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 16.dp)
                .clip(bloomShapes.medium),
            colors = ButtonDefaults.buttonColors(containerColor = pink900)
        ) {
            Text(
                text = "Create Account", style = textButtonStyle
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        TextButton(
            onClick = { /*TODO*/ }, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            val localString = compositionLocalOf { "Android" }
            CompositionLocalProvider(localString provides "Kotlin") {
                CompositionLocalProvider(localString provides "Compose") {
                    Text(text = "Kotlin", style = textButtonStyle, color = pink900)
                }
            }

        }
    }
}

@Composable
fun WelcomeTitle() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_light_logo)),
            contentDescription = null,
            modifier = Modifier
                .wrapContentWidth()
                .height(32.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp), contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Beautiful home garden solutions",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 16.sp, fontFamily = nunitoSansFamily, fontWeight = FontWeight.Light
                ),
                color = gray
            )
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "welcome") {
        composable(
            "welcome",
//            /{userName}/{userId}
//            arguments = listOf(navArgument("userName") { type = NavType.StringType },
//                navArgument("userId") { type = NavType.IntType })
        ) { navBackStackEntry ->
            WelcomePage(navController = navController)
        }



        composable("login") { LoginPage(navController = navController) }
        composable("home") { HomePage(navController = navController) }
    }


}