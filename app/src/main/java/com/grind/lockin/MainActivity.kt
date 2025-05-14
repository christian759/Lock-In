package com.grind.lockin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import java.util.Locale
import com.grind.lockin.ui.screen.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LockInApp()
        }
    }
}


@Composable
fun LockInApp(){

    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                val screens = listOf("home", "focus", "blocked", "stats", "settings")
                screens.forEach { screen ->
                    NavigationBarItem(
                        selected = currentDestination?.route == screen,
                        onClick = { navController.navigate(screen) },
                        icon = { Icon(Icons.Default.Favorite, contentDescription = screen) }, // replace icons
                        label = { Text(screen.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }) }
                    )
                }
            }
        }
    ) {
        NavHost(navController, startDestination = "home", Modifier.padding(it)) {
            composable("home") { HomeScreen() }
            composable("focus") { FocusScreen() }
            composable("blocked") { BlockedAppScreen() }
            composable("stats") { StatScreen() }
            composable("settings") { SettingScreen() }
        }
    }

}