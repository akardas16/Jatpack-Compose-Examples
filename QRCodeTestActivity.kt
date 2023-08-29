package com.example.testproject

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.example.testproject.ui.theme.TestProjectTheme
import com.github.alexzhirkevich.customqrgenerator.vector.QrCodeDrawable
import com.github.alexzhirkevich.customqrgenerator.vector.createQrVectorOptions
import com.github.alexzhirkevich.customqrgenerator.vector.style.QrVectorBallShape
import com.github.alexzhirkevich.customqrgenerator.vector.style.QrVectorColor
import com.github.alexzhirkevich.customqrgenerator.vector.style.QrVectorFrameShape
import com.github.alexzhirkevich.customqrgenerator.vector.style.QrVectorLogoPadding
import com.github.alexzhirkevich.customqrgenerator.vector.style.QrVectorLogoShape
import com.github.alexzhirkevich.customqrgenerator.vector.style.QrVectorPixelShape

class QRCodeTestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestProjectTheme {

                Greeting5()
            }
        }
    }
}

@Composable
fun Greeting5() {

    var text by remember {
        mutableStateOf("")
    }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){

        QRCode(text = text, modifier = Modifier.size(200.dp))

        TextField(value = text, onValueChange = {text = it},
            modifier = Modifier.padding(vertical = 32.dp))




    }

}


@Composable
fun QRCode(
    modifier: Modifier = Modifier,
    text: String = "This is QR text", enableLastEye: Boolean = true,
    color: Color = Color(0xFFBD22E7), enableGradient: Boolean = true,
    gradientColors: List<Color> = listOf(Color.Red, Color.Blue),
    gradientColorDirection: QrVectorColor.LinearGradient.Orientation = QrVectorColor.LinearGradient
        .Orientation.LeftDiagonal,
    enableLogo: Boolean = false,
    logo: Int? = null, logoShape: QrVectorLogoShape = QrVectorLogoShape.Circle,
) {

    val context = LocalContext.current
    AndroidView(factory = { ctx ->

        ImageView(ctx).apply {
            val options = createQrVectorOptions {

                padding = .01f

                fourthEyeEnabled = enableLastEye


                if (enableLogo) {
                    logo {
                        drawable = logo?.let { ContextCompat.getDrawable(ctx.findActivity(), it) }
                        size = .25f
                        padding = QrVectorLogoPadding.Natural(.2f)
                        shape = logoShape
                    }
                }

                colors {
                    dark = if (enableGradient) {
                        QrVectorColor.LinearGradient(
                            colors = listOf(
                                0f to gradientColors.first().toArgb(),
                                1f to gradientColors.last().toArgb(),
                            ),
                            orientation = gradientColorDirection
                        )
                    } else {
                        QrVectorColor
                            .Solid(color.toArgb())
                    }

                }
                shapes {
                    darkPixel = QrVectorPixelShape
                        .RoundCorners(.5f)
                    ball = QrVectorBallShape
                        .RoundCorners(.25f)
                    frame = QrVectorFrameShape
                        .RoundCorners(.25f)
                }
            }

            this.setImageBitmap(
                QrCodeDrawable({ text }, options = options)
                    .toBitmap(1024, 1024)
            )
        }
    }, update = {
        it.apply {
            val options = createQrVectorOptions {

                padding = .01f

                fourthEyeEnabled = enableLastEye


                if (enableLogo) {
                    logo {
                        drawable =
                            logo?.let { ContextCompat.getDrawable(context.findActivity(), it) }
                        size = .25f
                        padding = QrVectorLogoPadding.Natural(.2f)
                        shape = logoShape
                    }
                }

                colors {
                    dark = if (enableGradient) {
                        QrVectorColor.LinearGradient(
                            colors = listOf(
                                0f to gradientColors.first().toArgb(),
                                1f to gradientColors.last().toArgb(),
                            ),
                            orientation = gradientColorDirection
                        )
                    } else {
                        QrVectorColor
                            .Solid(color.toArgb())
                    }

                }
                shapes {
                    darkPixel = QrVectorPixelShape
                        .RoundCorners(.5f)
                    ball = QrVectorBallShape
                        .RoundCorners(.25f)
                    frame = QrVectorFrameShape
                        .RoundCorners(.25f)
                }
            }

            this.setImageBitmap(
                QrCodeDrawable({ text }, options = options)
                    .toBitmap(1024, 1024)
            )
        }
    }, modifier = modifier)

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview9() {
    TestProjectTheme {
        Greeting5()
    }
}