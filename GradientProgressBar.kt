@Composable
fun GradientProgressBar(
    progress: Float, min: Float = 0f, max: Float = 100f,
    foregroundColor: Color? = null, paddingHorizontal: Dp = 16.dp,
    gradientColors: List<Color> = listOf(
        Color(0xFF2A73DF),
        Color(0xFF0EC6DF)
    ),
    backgroundColor: Color = Color(0xFFDBDADA), height: Dp = 12.dp,
) {


    var newSize by remember {
        mutableStateOf(IntSize(0, 0))
    }

    val animatedIndicatorValue = remember {
        androidx.compose.animation.core.Animatable(initialValue = (progress * (newSize.width.toFloat() / max)))
    }

    LaunchedEffect(key1 = progress) {
        val newValue = if (progress > max) {
            newSize.width.toFloat()
        } else if (progress < min) {
            0f
        } else {
            (progress * (newSize.width.toFloat() / max))
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
            .background(Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Column(modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .padding(horizontal = 6.dp)
            .padding(horizontal = paddingHorizontal)
            .drawBehind {
                drawLine(
                    color = backgroundColor,
                    start = Offset(x = 0f, y = (newSize.height / 2).toFloat()),
                    end = Offset(
                        x = newSize.width.toFloat(),
                        y = (newSize.height / 2).toFloat()
                    ),
                    strokeWidth = height.toPx(), cap = StrokeCap.Round
                )
            }
            .drawBehind {
                if (progress > 0) {
                    if (foregroundColor == null) {
                        drawLine(
                            brush = Brush.horizontalGradient(gradientColors),
                            start = Offset(x = 0f, y = (newSize.height / 2).toFloat()),
                            end = Offset(
                                x = animatedIndicatorValue.value,
                                y = (newSize.height / 2).toFloat()
                            ),
                            strokeWidth = height.toPx(), cap = StrokeCap.Round
                        )

                    } else {
                        drawLine(
                            color = foregroundColor,
                            start = Offset(x = 0f, y = (newSize.height / 2).toFloat()),
                            end = Offset(
                                x = animatedIndicatorValue.value,
                                y = (newSize.height / 2).toFloat()
                            ),
                            strokeWidth = height.toPx(), cap = StrokeCap.Round
                        )
                    }
                }


            }

            .onSizeChanged { newSize = it }, horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

        }


    }
}
