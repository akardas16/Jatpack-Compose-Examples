@Composable
private fun Test() {

    var progress1 by remember {
        mutableFloatStateOf(0.0f)
    }

    var lineColor by remember {
        mutableStateOf(Color(0xFFF50000))
    }

    var text by remember { mutableStateOf("") }
    var iconVisibility by remember { mutableStateOf(false) }
    val animatedIndicatorValue = remember {
        Animatable(initialValue = 0f)
    }

    LaunchedEffect(key1 = progress1) {
        val newValue = progress1
        animatedIndicatorValue.animateTo(
            newValue,
            animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
        )
    }

    // This is the progress path which wis changed using path measure
    val pathWithProgress by remember {
        mutableStateOf(Path())
    }

    // using path
    val pathMeasure by remember { mutableStateOf(PathMeasure()) }

    val path = remember {
        Path()
    }
    
    Text(text = "Password: $text", modifier = Modifier.padding(vertical = 16.dp))


    //With solid background color
    BasicTextField(value = text, onValueChange = {
        text = it
        when(it.length){
            0 -> {
                progress1 = 0f
                lineColor = Color(0xFFF50000)
            }
            1 -> {
                progress1 = 1f / 8
                lineColor = Color(0xFFF50000)
            }
            2 -> {
                progress1 = 1f / 8 * 2
                lineColor = Color(0xFFF50000)
            }
            3 -> {
                progress1 = 1f / 8 * 3
                lineColor = Color(0xFFF50000)
            }
            4 -> {
                progress1 = 1f / 8 * 4
                lineColor = Color(0xFFFFB027)
            }
            5 -> {
                progress1 = 1f / 8 * 5
                lineColor = Color(0xFFFFB027)
            }
            6 -> {
                progress1 = 1f / 8 * 6
                lineColor = Color(0xFF30F519)
            }
            7 -> {
                progress1 = 1f / 8 * 7
                lineColor = Color(0xFF30F519)
            }
            8 -> {
                progress1 = 1f / 8 * 8
                lineColor = Color(0xFF30F519)
            }

        }
         },

        singleLine = true,
        cursorBrush = SolidColor(Color.Black.copy(0.7f)),
        textStyle = LocalTextStyle.current.copy(
            color = Color.Black.copy(0.7f)
        ),
        decorationBox = {innerTextField ->
            Canvas(modifier = Modifier
                .fillMaxWidth(0.92f)
                .height(52.dp)) {
                val width: Float = size.width
                val height: Float = size.height

                if (path.isEmpty) {
                    path.apply {
                        moveTo(0f, height / 2)
                        lineTo(0f,0f)
                        lineTo(width,0f)
                        lineTo(width,height)
                        lineTo(0f,height)
                        lineTo(0f,height / 2)
                    }
                }

                pathWithProgress.reset()

                pathMeasure.setPath(path, forceClosed = false)
                pathMeasure.getSegment(
                    startDistance = 0f,
                    stopDistance = pathMeasure.length * animatedIndicatorValue.value,
                    pathWithProgress,
                    startWithMoveTo = true
                )

                drawPath(color = lineColor, path = pathWithProgress, style = Stroke(7f, cap = StrokeCap.Round,
                    pathEffect = PathEffect.cornerPathEffect(4.dp.toPx())))



            }
            Row(verticalAlignment = Alignment.CenterVertically) {

                Box(modifier = Modifier
                    .weight(5f)
                    .padding(start = 12.dp)) {
                    if (text.isEmpty()) Text(text = "Enter Password", fontSize = 15.sp, color = Color.Gray,
                        modifier = Modifier.alpha(0.7f))

                    innerTextField()
                }
                IconButton(onClick = { iconVisibility = !iconVisibility },
                    modifier = Modifier.weight(1f)) {
                    if (iconVisibility){
                        Icon(imageVector = Icons.Rounded.Visibility, contentDescription = "",tint = Color.Black.copy(0.7f),modifier =
                        Modifier
                            .width(20.dp)
                            .height(20.dp))
                    }else {
                        Icon(imageVector = Icons.Rounded.VisibilityOff, contentDescription = "",tint = Color.Black.copy(0.7f),modifier =
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
                Color(0xFFE6E6E6),
                shape = RoundedCornerShape(6.dp)
            )
            .fillMaxWidth(0.92f)
            .height(44.dp)
    )

}
