package com.akardas.multiplatform

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Pause
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import chaintech.videoplayer.extension.formatMinSec
import chaintech.videoplayer.ui.component.AnimatedClickableIcon
import chaintech.videoplayer.util.CMPAudioPlayer

//Add dependency network.chaintech:compose-multiplatform-media-player in commonMain
@Composable
fun MyAudioPlayer(
    modifier: Modifier = Modifier, // Modifier for the composable
    url: String, // URL of the video
    isRepeat: Boolean = false, // State for repeat one
    didEndAudio: () -> Unit = {} // Callback when audio playback ends
){
    var isPause by remember { mutableStateOf(false) } // State for pausing/resuming audio
    var totalTime by remember { mutableStateOf(0) } // Total duration of the audio
    var currentTime by remember { mutableStateOf(0) } // Current playback time
    var isSliding by remember { mutableStateOf(false) } // Flag indicating if the seek bar is being slid
    var sliderTime: Int? by remember { mutableStateOf(null) } // Time indicated by the seek bar
    var isLoading by remember { mutableStateOf(true) } // Flag indicating audio buffer


    // Container for the audio player and control components
    Box(
        modifier = modifier, contentAlignment = Alignment.Center) {

        // Audio player component
        CMPAudioPlayer(
            modifier = modifier,
            url = url,
            isPause = isPause,
            totalTime = { totalTime = it }, // Update total time of the audio
            currentTime = {
                if (isSliding.not()) {
                    currentTime = it // Update current playback time
                    sliderTime = null // Reset slider time if not sliding
                }
            },
            isSliding = isSliding, // Pass seek bar sliding state
            sliderTime = sliderTime, // Pass seek bar slider time,
            isRepeat = isRepeat,
            loadingState = { isLoading = it },
            didEndAudio = didEndAudio
        )

        Column(
            modifier = Modifier, // Align the column to the bottom ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Row (
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp), // Occupy remaining horizontal space
                verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center
            ) {
                // Display the current playback time
                Text(
                    modifier = Modifier,
                    text = currentTime.formatMinSec(), // Format the current time to "MM:SS" format
                    color = Color.Black
                )

                // Slider for seeking through the media
                Slider(
                    modifier = Modifier.weight(1f),
                    value = currentTime.toFloat(), // Current value of the slider
                    onValueChange = {
                        currentTime = it.toInt()
                        isSliding = true
                        sliderTime = null
                    },
                    valueRange = 0f..totalTime.toFloat(), // Range of the slider
                    onValueChangeFinished = {
                        isSliding = false
                        sliderTime = currentTime
                    },
                    colors = SliderDefaults.colors(
                        thumbColor = Color.Cyan,
                        inactiveTrackColor = Color.Black.copy(0.4f),
                        activeTrackColor = Color.Cyan.copy(0.8f)
                    )
                )
                Text(
                    modifier = Modifier,
                    text = totalTime.formatMinSec(), // Format the total time to "MM:SS" format
                    color = Color.Black
                )

            }


            Box(contentAlignment = Alignment.Center) {
                // If pause/resume is enabled and icons are provided, show the appropriate icon

                AnimatedClickableIcon(
                    imageVector = if (isPause) Icons.Rounded.PlayArrow else Icons.Rounded.Pause,
                    contentDescription = "Play/Pause",
                    tint = Color.Black,
                    iconSize = 45.dp,
                    onClick = { isPause = isPause.not() }
                )

                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(52.dp),
                        color = Color.Blue
                    )
                }
            }
        }
    }
}
