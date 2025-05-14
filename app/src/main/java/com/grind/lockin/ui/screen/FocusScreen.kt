package com.grind.lockin.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun FocusScreen() {
    var focusTime by remember { mutableStateOf(25 * 60) }
    var isRunning by remember { mutableStateOf(false) }

    LaunchedEffect(isRunning) {
        if (isRunning) {
            while (focusTime > 0) {
                delay(1000)
                focusTime -= 1
            }
            isRunning = false
        }
    }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            String.format("%02d:%02d", focusTime / 60, focusTime % 60),
            style = MaterialTheme.typography.displayMedium
        )
        Spacer(Modifier.height(20.dp))
        Button(onClick = { isRunning = !isRunning }) {
            Text(if (isRunning) "Pause" else "Start Focus")
        }
    }
}
