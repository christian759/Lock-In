package com.grind.lockin.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun BlockedAppsScreen(viewModel: LockInViewModel = viewModel()) {
    val apps by viewModel.blockedApps.observeAsState(emptyList())

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Blocked Apps", style = MaterialTheme.typography.headlineSmall)

        LazyColumn {
            items(apps) { app ->
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
