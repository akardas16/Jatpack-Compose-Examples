@Composable
fun AnimationLoadingDots(){

    val state by remember {
        mutableFloatStateOf(0f)
    }
    val animations by remember {
        mutableStateOf(listOf(Animatable(initialValue = 0f),Animatable(initialValue = 0f),Animatable(initialValue = 0f)))
    }

    animations.forEachIndexed { index,item ->
        LaunchedEffect(key1 = state){

            delay(100 * index.toLong())
            item.animateTo(targetValue = 60f, animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1200, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ))
        }
    }

    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            animations.forEach { anim ->
                Surface(shape = CircleShape, color = Color.Magenta, modifier = Modifier
                    .size(18.dp)
                    .offset(y = anim.value.dp)) {}
            }
        }


    }
}
