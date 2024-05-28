package com.mrhwsn.patternlock

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.OptIn
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds


/*
Required Dependencies
implementation("androidx.media3:media3-exoplayer:1.3.1")
implementation("androidx.media3:media3-ui:1.3.1")
*/

@OptIn(UnstableApi::class)
@kotlin.OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoPlayer(
    modifier: Modifier,
    playWhenReadyVideo: Boolean = false,
    uri: String = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4",
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val scope = rememberCoroutineScope()
    val exoPlayer = remember(context) {
        ExoPlayer.Builder(context).build().apply {
            playWhenReady = playWhenReadyVideo
            prepare()

            repeatMode = Player.REPEAT_MODE_ONE
            setMediaItem(MediaItem.fromUri(uri))
        }
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val interactionSource2 = remember {
        MutableInteractionSource()
    }
    var sliderPosition by remember {
        mutableFloatStateOf(0f)
    }
    var showController by remember { mutableStateOf(true) }
    var isPlayingVideo by remember { mutableStateOf(playWhenReadyVideo) }
    var currentTime by remember {
        mutableStateOf("00:00")
    }

    var totalDuration by remember {
        mutableStateOf("00:00")
    }

    lifecycleOwner.ListenLifecycle(onStop = {
        exoPlayer.pause()
    })

    DisposableEffect(key1 = exoPlayer) {
        onDispose {
            exoPlayer.release()
        }
    }
    LaunchedEffect(key1 = showController) {
        if (showController) {
            delay(5000)
            showController = false
        }
    }
    Box(
        modifier = modifier.clickable(
            interactionSource = interactionSource,
            indication = null
        ) {
            showController = !showController
        }, contentAlignment = Alignment.Center
    ) {

        AndroidView(modifier = Modifier.fillMaxSize(), factory = {

            PlayerView(context).apply {
                player = exoPlayer
                exoPlayer.addListener(object : Player.Listener {

                    override fun onPlaybackStateChanged(playbackState: Int) {
                        super.onPlaybackStateChanged(playbackState)
                        val duration = millisToDate(exoPlayer.duration)
                        totalDuration = duration
                    }

                    override fun onIsPlayingChanged(isPlaying: Boolean) {
                        super.onIsPlayingChanged(isPlaying)
                        isPlayingVideo = isPlaying
                    }

                })
                useController = false
                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
                layoutParams = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )

            }
        }, update = {
            it.player = exoPlayer

            scope.launch(Dispatchers.Main) {
                exoPlayer.currentPositionFlow().collect { currentPosition ->
                    currentTime = millisToDate(currentPosition)
                    sliderPosition = currentPosition.toFloat()
                }

            }

        })
        AnimatedVisibility(
            visible = showController, enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x3C141111)), contentAlignment = Alignment.Center
            ) {
                Icon(painter = painterResource(id = if (isPlayingVideo) R.drawable.play else R.drawable.pause),
                    contentDescription = "", modifier = Modifier
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            if (exoPlayer.isPlaying) exoPlayer.pause() else exoPlayer.play()
                        }
                        .size(45.dp), tint = Color.Unspecified)


            }
            if (exoPlayer.duration > 0) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {

                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp, horizontal = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(
                            4.dp,
                            Alignment.CenterHorizontally
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(text = currentTime, color = Color.White)


                        Slider(modifier = Modifier.weight(1f), thumb = {
                            SliderDefaults.Thumb(
                                interactionSource = interactionSource2,
                                thumbSize = DpSize(16.dp, 16.dp),
                            )
                        }, value = sliderPosition, onValueChange = {
                            sliderPosition = it
                            exoPlayer.seekTo(sliderPosition.toLong())
                            currentTime = millisToDate(sliderPosition.toLong())
                        }, valueRange = 0f..exoPlayer.duration.toFloat())


                        Text(text = totalDuration, color = Color.White)
                    }

                }

            }


        }

    }


}

fun Player.currentPositionFlow() = flow {
    while (true) {
        if (isPlaying) emit(currentPosition)
        delay(1.seconds)
    }
}.flowOn(Dispatchers.Main)

/*fun Player.isPlayingVideo() = flow {
    while (true) {

        emit(isPlaying)

    }
}.flowOn(Dispatchers.Main)*/


/*fun Player.remainingTimeFlow() = flow {
    while (true) {
        if (isPlaying) emit(millisToDate(duration.minus(currentPosition)))
        delay(1.seconds)
    }
}.flowOn(Dispatchers.Main)*/


private fun millisToDate(millis: Long?): String {
    if (millis == null) {
        return "00:00"
    } else {
        val dateFormat = "mm:ss"
        val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
        val calendar = Calendar.getInstance()

        calendar.timeInMillis = millis
        return formatter.format(calendar.time)
    }

}

@Composable
private fun LifecycleOwner.ListenLifecycle(
    onCreate: () -> Unit = {},
    onStart: () -> Unit = {},
    onResume: () -> Unit = {},
    onStop: () -> Unit = {},
    onPause: () -> Unit = {},
    onDestroy: () -> Unit = {},
    onRemoved: () -> Unit = {},
) {
    DisposableEffect(key1 = this) {
        val observer = LifecycleEventObserver { source, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> onCreate()
                Lifecycle.Event.ON_RESUME -> onResume()
                Lifecycle.Event.ON_STOP -> onStop()
                Lifecycle.Event.ON_PAUSE -> onPause()
                Lifecycle.Event.ON_START -> onStart()
                Lifecycle.Event.ON_ANY -> {
                }

                Lifecycle.Event.ON_DESTROY -> onDestroy()

            }
        }
        this@ListenLifecycle.lifecycle.addObserver(observer)

        onDispose {
            onRemoved()
            this@ListenLifecycle.lifecycle.removeObserver(observer)
        }
    }
}
