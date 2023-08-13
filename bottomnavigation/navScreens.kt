package com.example.testproject.bottomnavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
            .wrapContentSize(Alignment.Center)
    ) {
        SetSystemColor(colorStatus = Color.Red)
        Text(
            text = "Home Screen",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }

}

@Composable
fun SearchScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
            .wrapContentSize(Alignment.Center)
    ) {
        SetSystemColor(colorStatus = Color.Cyan)
        Text(
            text = "Search Screen",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}

@Composable
fun SettingScreen(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
            .wrapContentSize(Alignment.Center)
    ) {
        SetSystemColor(colorStatus = Color.Blue)
        Text(
            text = "Setting Screen",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally).clickable {
                navController.navigate(BottomNavItem.Another.route)
              },
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}

@Composable
fun AnotherScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .wrapContentSize(Alignment.Center)
    ) {
        SetSystemColor(colorStatus = Color.White, colorNav = Color.White)
        Text(
            text = "Another Screen",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}