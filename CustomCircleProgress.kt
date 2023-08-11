@Composable
fun CustomCircleProgress(min:Float = 0f, max:Float = 100f,
                         foregroundColor: Color? = null, gradientColors:List<Color> = listOf(Color(0xFF0A62E4),Color(0xFF7EB1FD)),
                         backgroundColor:Color = Color(0xFFDBDADA), size: Dp = 300.dp) {

    var count by remember {
        mutableStateOf(0f)
    }

    val animatedIndicatorValue = remember {
        androidx.compose.animation.core.Animatable(initialValue = (count * (240 / max)))
    }

    LaunchedEffect(key1 = count) {
        val newValue = if (count > max) {
            count = max
            240f
        } else if (count < min) {
            count = min
            0f
        } else {
            (count * (240 / max))
        }
        animatedIndicatorValue.animateTo(
            newValue,
            animationSpec = spring(
                Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
    }

    Column(
        Modifier
            .background(Color.Transparent)
            .clickable {
                count += 10
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Column(modifier = Modifier
            .size(size)
            .padding(size / 10)
            .drawBehind {
                drawArc(
                    color = backgroundColor, startAngle = 150f,
                    sweepAngle = 240f, useCenter = false,
                    style = Stroke(width = 64f, cap = StrokeCap.Round)
                )
            }
            .drawBehind {
                if (foregroundColor == null){
                    drawArc(
                        brush = Brush.verticalGradient(gradientColors), startAngle = 150f,
                        sweepAngle = animatedIndicatorValue.value, useCenter = false,
                        style = Stroke(width = 64f, cap = StrokeCap.Round)
                    )
                }else{
                    drawArc(
                        color = foregroundColor, startAngle = 150f,
                        sweepAngle = animatedIndicatorValue.value, useCenter = false,
                        style = Stroke(width = 64f, cap = StrokeCap.Round)
                    )
                }

            }
            , horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            Text(text = "${count.toInt()}", fontSize = 45.sp,
                fontWeight = FontWeight.Bold, color = Color.Gray)
        }



    }
}
