package com.grind.lockin.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.grind.lockin.data.model.BlockedApp
import com.grind.lockin.viewmodel.LockInViewModel

@Composable
fun BlockedAppScreen(viewModel: LockInViewModel = viewModel()) {
    val apps by viewModel.blockedApps.collectAsState(initial = emptyList())

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Blocked Apps", style = MaterialTheme.typography.headlineSmall)

        LazyColumn {
            items(apps) { app: BlockedApp ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.LightGray)
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(app.appName)
                        IconButton(onClick = { viewModel.removeBlockedApp(app) }) {
                            Icon(Icons.Default.Delete, contentDescription = "Unblock")
                        }
                    }
                }
            }
        }
    }
}
