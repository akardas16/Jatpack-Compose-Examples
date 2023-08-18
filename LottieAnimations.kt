// implementation "com.airbnb.android:lottie-compose:6.1.0" 
// maven { url "https://oss.sonatype.org/content/repositories/snapshots/" }
// more info https://github.com/airbnb/lottie/blob/master/android-compose.md
@Composable
fun LottieAnimations(){
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.Asset("loading.json"))

    val progress by animateLottieCompositionAsState(composition = composition,
        iterations = LottieConstants.IterateForever)

    val composition2 by rememberLottieComposition(
        spec = LottieCompositionSpec.Asset("flower.json"))

    val progress2 by animateLottieCompositionAsState(composition = composition2,
        iterations = 2)//Two times will repeat

    LottieAnimation(composition = composition, progress = { progress },
        modifier = Modifier.size(150.dp))

    LottieAnimation(composition = composition2, progress = { progress2 },
        modifier = Modifier.size(200.dp))


}
