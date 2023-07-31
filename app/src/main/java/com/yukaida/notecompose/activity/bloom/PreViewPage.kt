package com.yukaida.notecompose.activity.bloom

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

@Preview
@Composable
fun HomePagePre(){
    HomePage(rememberNavController())
}

@Preview
@Composable
fun LoginPagePre(){
    LoginPage(rememberNavController())
}


@Preview
@Composable
fun WelcomePagePre(){
    WelcomePage(navController=rememberNavController())
}

