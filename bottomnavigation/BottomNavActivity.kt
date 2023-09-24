package com.example.testproject.bottomnavigation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.testproject.ui.theme.TestProjectTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController


class BottomNavActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            TestProjectTheme {
                // A surface container using the 'background' color from the theme
                // Remember a SystemUiController


                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BottomNavScreenView()
                }
            }
        }
    }
}


@Composable
fun SetSystemColor(colorStatus: Color,colorNav:Color =  Color(0xFF232A3D)){
    val systemUiController = rememberSystemUiController()
    val darkTheme = isSystemInDarkTheme()
    SideEffect {
       scope.launch { 
            delay(80)
            systemUiController.setStatusBarColor(
                color = if (darkTheme) Color.Blue else colorStatus,
                darkIcons = true
            )
        }


        systemUiController.setNavigationBarColor(
            color = if (darkTheme) Color.Blue else colorNav,
            darkIcons = true
        )
    }
}
@Composable
fun Greeting3(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    TestProjectTheme {
        Greeting3("Android")
    }
}
