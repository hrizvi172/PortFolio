package com.habib.portfolio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.habib.portfolio.data.PortfolioData
import com.habib.portfolio.navigation.Screen
import com.habib.portfolio.ui.screens.*
import com.habib.portfolio.ui.theme.PortfolioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val system = isSystemInDarkTheme()
            var dark by rememberSaveable { mutableStateOf(system) }
            PortfolioTheme(dark) { PortfolioApp(dark) { dark = !dark } }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PortfolioApp(dark: Boolean, onToggleTheme: () -> Unit) {
    val nav = rememberNavController()
    val route = nav.currentBackStackEntryAsState().value?.destination?.route
    fun go(s: Screen) = nav.navigate(s.route) {
        popUpTo(nav.graph.findStartDestination().id) { saveState = true }
        launchSingleTop = true; restoreState = true
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(PortfolioData.NAME) },
                actions = {
                    IconButton(onClick = onToggleTheme) {
                        Icon(if (dark) Icons.Rounded.LightMode else Icons.Rounded.DarkMode, "Toggle theme")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        },
        bottomBar = {
            NavigationBar {
                Screen.items.forEach { s ->
                    NavigationBarItem(route == s.route, { go(s) }, { Icon(s.icon, s.label) }, label = { Text(s.label) })
                }
            }
        }
    ) { pad ->
        NavHost(
            nav, Screen.Home.route, Modifier.padding(pad),
            enterTransition = { fadeIn(tween(300)) + slideInVertically(tween(300)) { it / 20 } },
            exitTransition = { fadeOut(tween(200)) },
            popEnterTransition = { fadeIn(tween(300)) },
            popExitTransition = { fadeOut(tween(200)) }
        ) {
            composable(Screen.Home.route) { HomeScreen({ go(Screen.Skills) }, { go(Screen.Contact) }) }
            composable(Screen.Skills.route) { SkillsScreen() }
            composable(Screen.Contact.route) { ContactScreen() }
        }
    }
}
