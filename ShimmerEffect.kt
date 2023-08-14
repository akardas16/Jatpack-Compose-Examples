package com.example.testproject

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun AnimatedShimmer(){

    val shimmerColors = listOf(
        Color.LightGray.copy(0.6f),
        Color.LightGray.copy(0.2f),
        Color.LightGray.copy(0.6f))

    val transition = rememberInfiniteTransition(label = "")
    val moveAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(animation = tween(durationMillis = 1000, easing = FastOutSlowInEasing)),
        label = ""
    )

    val brush = Brush.linearGradient(colors = shimmerColors,
        start = Offset.Zero, end = Offset(x = moveAnimation.value,moveAnimation.value))
    ShimmerComposeUI(brush = brush)

}

@Composable
fun ShimmerComposeUI(brush: Brush){

    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp), verticalAlignment = Alignment.CenterVertically) {

        Box(modifier = Modifier.padding(horizontal = 12.dp)
            .size(64.dp)
            .clip(CircleShape)
            .background(brush = brush)
            .padding())

        Column(modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(horizontal = 12.dp), verticalArrangement = Arrangement.SpaceEvenly) {

            Box(modifier = Modifier
                .height(18.dp)
                .fillMaxWidth(0.9f)
                .clip(RoundedCornerShape(50))
                .background(brush = brush))

            Box(modifier = Modifier
                .height(18.dp)
                .fillMaxWidth(0.5f)
                .clip(RoundedCornerShape(50))
                .background(brush = brush))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShimmerView(){
    AnimatedShimmer()
}


