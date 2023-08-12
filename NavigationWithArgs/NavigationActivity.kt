package com.example.testproject.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.testproject.ui.theme.TestProjectTheme

class NavigationActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestProjectTheme {
                // A surface container using the 'background' color from the theme
                navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}



//HomeScreen
@Composable
fun HomeScreen(navController: NavController){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Red.copy(0.6f)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {


        Text(text = "Home Screen", fontSize = 24.sp, color = Color.White)
        Button(onClick = {
            navController.navigate(route = Screen.Second.route)
        }) {
            Text(text = "Navigate")
        }


        Button(onClick = {
            navController.navigate(route = Screen.ThirdScreen.passUserData("Mehmet", 28))
        }) {
            Text(text = "Navigate to Third")
        }
    }
}


//SecondScreen
@Composable
fun SecondScreen(navController: NavController){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Blue.copy(0.6f)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Text(text = "Second Screen", fontSize = 24.sp, color = Color.White)
        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Back")
        }
    }
}


//ThirdScreen
@Composable
fun ThirdScreen(navController: NavController,username:String?){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Green.copy(0.6f)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Text(text = "Third Screen", fontSize = 24.sp, color = Color.White)
        Text(text = "$username", fontSize = 16.sp, color = Color.White)
        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Back")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    TestProjectTheme {
       val navController = rememberNavController()
        SetupNavGraph(navController = navController)
    }
}