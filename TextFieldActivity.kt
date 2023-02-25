package com.akardas.jatpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import com.akardas.jatpackcompose.ui.theme.JatpackComposeTheme

class TextFieldActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JatpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly) {
                        TextFields()
                    }
                }
            }
        }
    }
}

@Composable
fun TextFields() {
    var text by remember { mutableStateOf("") }
    var iconVisibility by remember { mutableStateOf(false) }




    //With solid background color
    BasicTextField(value = text, onValueChange = {text = it},

        singleLine = true,
        cursorBrush = SolidColor(Color.White),
        textStyle = LocalTextStyle.current.copy(
            color = Color.White
        ),
        decorationBox = {innerTextField ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Rounded.Lock, contentDescription = "",
                    tint = Color.White, modifier = Modifier
                        .weight(1f)
                        .width(20.dp)
                        .height(20.dp))
                Box(modifier = Modifier.weight(5f)) {
                    if (text.isEmpty()) Text(text = "Enter Password", color = Color.Gray,
                        modifier = Modifier.alpha(0.7f))

                    innerTextField()
                }
                IconButton(onClick = { iconVisibility = !iconVisibility },
                    modifier = Modifier.weight(1f)) {
                    if (iconVisibility){
                        Icon(imageVector = Icons.Rounded.Visibility, contentDescription = "",tint = Color.White,modifier =
                        Modifier
                            .width(20.dp)
                            .height(20.dp))
                    }else {
                        Icon(imageVector = Icons.Rounded.VisibilityOff, contentDescription = "",tint = Color.White,modifier =
                        Modifier
                            .width(20.dp)
                            .height(20.dp))
                    }
                }
            }
        },
        visualTransformation = if(iconVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(autoCorrect = false,
            keyboardType = KeyboardType.Password, imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(onNext = { println("onNext clicked") }),

        modifier = Modifier
            .background(
                colorResource(id = R.color.edittextback),
                shape = RoundedCornerShape(16.dp)
            )
            .fillMaxWidth(0.8f)
            .height(44.dp)
        )


    //with border stroke
    BasicTextField(value = text, onValueChange = {text = it},

        singleLine = true,
        cursorBrush = SolidColor(colorResource(id = R.color.edittextback)),
        textStyle = LocalTextStyle.current.copy(
            color = colorResource(id = R.color.edittextback)
        ),
        decorationBox = {innerTextField ->
            Row(verticalAlignment = Alignment.CenterVertically) {

                Box(modifier = Modifier.weight(5f)) {
                    if (text.isEmpty()) Text(text = "Enter Password", color = Color.Gray,
                        modifier = Modifier
                            .alpha(0.7f))

                    innerTextField()
                }
                IconButton(onClick = { iconVisibility = !iconVisibility },
                    modifier = Modifier.weight(1f)) {
                    if (iconVisibility){
                        Icon(imageVector = Icons.Rounded.Visibility, contentDescription = "",tint = Color.Gray,modifier =
                        Modifier
                            .width(20.dp)
                            .height(20.dp))
                    }else {
                        Icon(imageVector = Icons.Rounded.VisibilityOff, contentDescription = "",tint = Color.Gray,modifier =
                        Modifier
                            .width(20.dp)
                            .height(20.dp))
                    }
                }
            }
        },
        visualTransformation = if(iconVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(autoCorrect = false,
            keyboardType = KeyboardType.Password, imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(onNext = { println("onNext clicked") }),

        modifier = Modifier
            .border(
                BorderStroke(width = 2.dp, colorResource(id = R.color.edittextback)),
                shape = RoundedCornerShape(16.dp)
            ).padding(start = 16.dp)
            .fillMaxWidth(0.8f)
            .height(44.dp)
    )


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    JatpackComposeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly) {
                TextFields()
            }
        }
    }
}

@Composable
private fun CustomTextField(
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    placeholderText: String = "Placeholder",
    fontSize: TextUnit = MaterialTheme.typography.body2.fontSize
) {
    var text by remember { mutableStateOf("") }
    BasicTextField(modifier = modifier
        .background(
            MaterialTheme.colors.surface,
            MaterialTheme.shapes.small,
        ),
        value = text,
        onValueChange = {
            text = it
        },
        singleLine = true,
        cursorBrush = SolidColor(MaterialTheme.colors.primary),
        textStyle = LocalTextStyle.current.copy(
            color = MaterialTheme.colors.onSurface,
            fontSize = fontSize
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) leadingIcon()
                Box(Modifier.weight(1f)) {
                    if (text.isEmpty()) Text(
                        placeholderText,
                        style = LocalTextStyle.current.copy(
                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.3f),
                            fontSize = fontSize
                        )
                    )
                    innerTextField()
                }
                if (trailingIcon != null) trailingIcon()
            }
        }
    )
}