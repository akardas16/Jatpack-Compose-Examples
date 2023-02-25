package com.akardas.jatpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.akardas.jatpackcompose.ui.theme.JatpackComposeTheme

class CustomButtonsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JatpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Buttons()
                    }

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Buttons() {

    var clicked by remember { mutableStateOf(false) }

    Surface(onClick = { clicked = clicked.not() }, border = BorderStroke(width = 1.dp, color = Color.Gray),
        shape = RoundedCornerShape(8.dp)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                )) {

            Icon(painter = painterResource(id = R.drawable.google_icon), contentDescription = "google",
                tint = Color.Unspecified, modifier = Modifier
                    .width(24.dp)
                    .height(24.dp))

            Spacer(modifier = Modifier.width(12.dp))
            if (clicked.not()){
                Text(text = "Sign with Google", color = Color.Black)
            }else{
                Text(text = "Creating Account...", color = Color.Black)
            }

            Spacer(modifier = Modifier.width(12.dp))
            if (clicked){
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp), strokeWidth = 3.dp,
                    color = colorResource(
                    id = R.color.edittextback
                ))
                Spacer(modifier = Modifier.width(12.dp))
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview6() {
    JatpackComposeTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Buttons()
            }

        }
    }
}