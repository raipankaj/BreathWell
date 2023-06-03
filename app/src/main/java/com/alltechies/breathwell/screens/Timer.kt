package com.alltechies.breathwell.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.alltechies.breathwell.data.Breath
import com.alltechies.breathwell.viewmodel.BreathViewModel
import com.alltechies.timer.CountDownTimer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleTimer(
    breathViewModel: BreathViewModel,
    type: String?, onBackPressed: () -> Unit
) {

    //Observe the breath object which will auto update once value is fetched inside ViewModel
    val breath by breathViewModel.breathExercise.collectAsStateWithLifecycle()

    //Fetch the exercise based on the type
    LaunchedEffect(key1 = Unit) {
        breathViewModel.getExerciseBasedOnType(type)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = type ?: "", fontWeight = FontWeight.ExtraBold)
                },
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { paddingValues ->
            Timer(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                breath = breath,
                onBackPressed = onBackPressed
            )
        }
    )
}

@Composable
fun Timer(
    modifier: Modifier,
    breath: Breath?,
    onBackPressed: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        if (breath == null) {
            ErrorType()
        } else {
            CountDownScheduler(
                breath = breath,
                onBackPressed = onBackPressed
            )
        }
    }
}

@Composable
fun CountDownScheduler(breath: Breath, onBackPressed: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        AsyncImage(
            model = breath.url, contentDescription = "background image",
            contentScale = ContentScale.Crop, modifier = Modifier
                .fillMaxSize()
                .blur(12.dp)
        )

        CountDownTimer(
            actionList = breath.action,
            dialerBackgroundColor = Color(0xFFDB4437),
            dialerProgressColor = Color(0xFF333333),
            dialerBorderColor = Color.White,
            onTimerExpired = {
                onBackPressed()
            }
        )
    }
}

@Composable
fun ErrorType() {
    Text(text = "Unable to find appropriate type of breathing exercise, please try again later")
}