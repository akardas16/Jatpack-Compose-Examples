package com.akardas.jatpackcompose

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.akardas.jatpackcompose.ui.theme.JatpackComposeTheme


class TestComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JatpackComposeTheme {
                // A surface container using the 'background' color from the theme

                MyContent()
            }
        }
    }
}



@Composable
fun NavigateSignUp(onClick: () -> Unit){
    ClickableText(buildAnnotatedString {
        append("Not a member? ")
        withStyle(style = SpanStyle(color = Color.Magenta,
            fontWeight = FontWeight.Bold, textDecoration = TextDecoration.Underline
        )){
            append("Sign Up")
        }
    }, onClick = {onClick.invoke()} )
}

@Composable
fun MyContent(){
    Surface(modifier = Modifier.fillMaxSize()) {
        val mContext = LocalContext.current
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly) {

            NavigateSignUp {
                showMessage(context = mContext,"Navigate to Sign Up")
            }
            NavigateSignIn {
                showMessage(context = mContext,"Navigate to Sign In")
            }
        }

    }
}

@Composable
fun NavigateSignIn(onClick: () -> Unit){
    ClickableText(buildAnnotatedString {
        append("Already a member? ")
        withStyle(style = SpanStyle(color = Color.Magenta,
            fontWeight = FontWeight.Bold, textDecoration = TextDecoration.Underline
        )){
            append("Sign In")
        }
    }, onClick = {onClick.invoke()} )
}

fun showMessage(context:Context,message:String){
    Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    JatpackComposeTheme {
        MyContent()

    }
}