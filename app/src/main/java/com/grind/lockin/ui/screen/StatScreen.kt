package com.grind.lockin.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.grind.lockin.viewmodel.AppStatsViewModel

@Composable
fun StatScreen() {
    val viewModel = remember { AppStatsViewModel() }
    val todayUsage by viewModel.getTodayUsage().collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        Text("Today's Screen Time", style = MaterialTheme.typography.headlineMedium)
        Text(
            text = "${(todayUsage / 1000) / 60} min",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text("Usage Graph", style = MaterialTheme.typography.titleLarge)
        CircularProgressIndicator(
        progress = { (todayUsage % 3600000L) / 3600000f },
        modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp),
        )
    }
}
