package com.akardas.jatpackcompose.dialog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.akardas.jatpackcompose.MyViewModel
import com.akardas.jatpackcompose.ui.theme.JatpackComposeTheme

class ExampleDialogs : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JatpackComposeTheme {
                // A surface container using the 'background' color from the theme

                Screen(MyViewModel())
            }
        }
    }
}

@Composable
fun Screen(viewModel: MyViewModel){
    val isAnimatedDialogDisplayed by viewModel.isAnimatedDialogDisplayed.observeAsState(false)
    val isShown = viewModel.isShown.collectAsState(false)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(onClick = { viewModel.showDialog() }) {
                        Text(text = "Show Pop Up")
                    }
                }
                if (isShown.value){
                    InfoDialog(
                        buttonAction = {  },
                        onDismissRequest = { viewModel.hideDialog() }
                    )
                }
            }
        }
    }



}

@Preview(showBackground = true)
@Composable
fun DefaultPreview5() {
    JatpackComposeTheme {
        Screen(MyViewModel())

    }
}

@Composable
fun InfoDialog(buttonAction: () -> Unit,
                   onDismissRequest: () -> Unit
) {
    AnimatedDialog(onDismissRequest = onDismissRequest) { animatedTransitionDialogHelper ->


        Surface(
            shape = RoundedCornerShape(10.dp),
            color = Color.White,
            modifier = Modifier.fillMaxWidth(0.82f)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(imageVector = Icons.Rounded.Info, contentDescription = "İnfo", modifier = Modifier
                    .width(28.dp)
                    .height(28.dp),tint = Color.Cyan)
                Text("This is an animated dialog")
                Button(onClick = {
                    buttonAction.invoke()
                    animatedTransitionDialogHelper::triggerAnimatedDismiss.invoke()


                }, shape = CircleShape) {
                    Text("Close".uppercase(), fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 16.dp))
                }
            }
        }
    }
}


