//Normal Button
Surface(
           modifier = Modifier
               .width(200.dp)
               .height(45.dp)
               .bounceClick {
                   glideState = !glideState
                   blurRadius = 0

               },
           color = Color.Red,
           shape = RoundedCornerShape(percent = 50),
           shadowElevation = 8.dp
       ) {
           Row(verticalAlignment = Alignment.CenterVertically,
               horizontalArrangement = Arrangement.Center) {
               Icon(imageVector = Icons.Rounded.GppGood, contentDescription = null,
                   tint = Color.White, modifier = Modifier.padding(horizontal = 6.dp))
               Text(text = "Click Me", color = Color.White)
           }


       }

//Gradient Button
       Surface(modifier = Modifier.bounceClick {  },
           color = Color.Transparent,
           shape = RoundedCornerShape(50), shadowElevation = 8.dp) {
           Column(modifier = Modifier.background(brush = Brush.verticalGradient(
                   listOf(Color(0xFF00A1A7), Color(0xFF15DAE0))
               )
           )) {
               Text(text = "Share", color = Color.White,
                   modifier = Modifier.padding(horizontal = 45.dp, vertical = 10.dp))
           }

       }



       //Google Icon
       Surface(
           modifier = Modifier


               .height(45.dp)

               .bounceClick {

               },
           color = Color.White.copy(0.8f),
           shape = RoundedCornerShape(8.dp),
           shadowElevation = 8.dp
       ) {
           Row(verticalAlignment = Alignment.CenterVertically,
               horizontalArrangement = Arrangement.Center,
               modifier = Modifier.padding(horizontal = 6.dp)) {
               Icon(painter = painterResource(id = R.drawable.google_icon), contentDescription = null,
                   tint = Color.Unspecified,
                   modifier = Modifier
                       .padding(horizontal = 4.dp)
                       .width(32.dp)
                       .height(32.dp))
               Text(text = "Sign In with Google", color = Color.Gray,
                   modifier = Modifier.padding(horizontal = 4.dp), fontWeight = FontWeight.Bold
               )
           }


       }

       //Bounce Click
       private fun Modifier.bounceClick(
    scaleDown: Float = 0.92f,
    onClick: () -> Unit
) = composed {

    val interactionSource = remember { MutableInteractionSource() }

    val animatable = remember {
        androidx.compose.animation.core.Animatable(1f)
    }

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is PressInteraction.Press -> animatable.animateTo(scaleDown)
                is PressInteraction.Release -> animatable.animateTo(1f)
                is PressInteraction.Cancel -> animatable.animateTo(1f)
            }
        }
    }

    Modifier
        .graphicsLayer {
            val scale = animatable.value
            scaleX = scale
            scaleY = scale
        }
        .clickable(
            interactionSource = interactionSource,
            indication = null
        ) {
            onClick()
        }
}
