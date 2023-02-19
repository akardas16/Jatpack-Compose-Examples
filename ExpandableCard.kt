package com.akardas.jatpackcompose

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.akardas.jatpackcompose.ui.theme.JatpackComposeTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpandableCard(){
    var expandState by remember {mutableStateOf(false)}
    val arrowRotate by animateFloatAsState(targetValue = if (expandState) 180f else 0f)

    Card(onClick = {expandState = !expandState},
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(animationSpec = tween(durationMillis = 600, easing = LinearOutSlowInEasing))
           .padding(horizontal = 8.dp),
        shape = RoundedCornerShape(8.dp)) {
        Column(Modifier.fillMaxWidth().background(
            Color.Gray
        ).coloredShadow(shadowRadius = 20.dp, offsetY = 24.dp, color = Color.Gray)
            .padding(vertical = 8.dp) ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "My Title", fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    modifier = Modifier.weight(6f).padding(start = 4.dp), color = Color.White)
                Icon(imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "ArrowDropDown", tint = Color.White,
                    modifier = Modifier
                        .rotate(arrowRotate)

                        .weight(1f).alpha(0.7f).width(28.dp).height(28.dp))
            }

            if (expandState){
                Text(text = "I tried setting the two pages Home and Post at the same level with no " +
                        "luck (see comments). I read the doc and another answer on SO and I think I follow " +
                        "everything done. " +
                        "I might be missing something obvious.",Modifier.padding(horizontal = 8.dp), color = Color.White)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview7() {
    JatpackComposeTheme {
        ExpandableCard()
    }
}

@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.coloredShadow(
    color: Color,
    alpha: Float = 0.2f,
    borderRadius: Dp = 0.dp,
    shadowRadius: Dp = 20.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp
) = composed {

    val shadowColor = color.copy(alpha = alpha).toArgb()
    val transparent = color.copy(alpha= 0f).toArgb()

    this.drawBehind {

        this.drawIntoCanvas {
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            frameworkPaint.color = transparent

            frameworkPaint.setShadowLayer(
                shadowRadius.toPx(),
                offsetX.toPx(),
                offsetY.toPx(),
                shadowColor
            )
            it.drawRoundRect(
                0f,
                0f,
                this.size.width,
                this.size.height,
                borderRadius.toPx(),
                borderRadius.toPx(),
                paint
            )
        }
    }
}