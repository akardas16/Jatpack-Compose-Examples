@Composable
fun SpecialDialog(showDialog:Boolean, onChanged:(isShown:Boolean) -> Unit, properties: DialogProperties = DialogProperties(),
                  content: @Composable () -> Unit){

    val animation by remember {
        mutableStateOf(androidx.compose.animation.core.Animatable(initialValue = 1f))
    }

    var show by remember {
        mutableStateOf(false)
    }


    LaunchedEffect(key1 = showDialog){

        if (showDialog) show = true
        launch {
            if (showDialog.not() and show) {
                delay(350)
                show = false
            }
        }
        launch {
            delay(75)
            animation.animateTo(targetValue = 1.05f,
                animationSpec = spring(Spring.DampingRatioMediumBouncy,Spring.StiffnessMediumLow))
        }

        launch {
            delay(175)
            animation.animateTo(targetValue = 1f,
                animationSpec = spring(Spring.DampingRatioMediumBouncy,Spring.StiffnessMediumLow))
        }


    }


    if (show){

        Dialog(onDismissRequest = {
            onChanged(false)

        }, properties = properties) {

            Box(modifier = Modifier.scale(animation.value)){
                content()
            }
        }
    }

}
