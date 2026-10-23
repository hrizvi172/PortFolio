package com.habib.portfolio.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Code
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Mail
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    data object Home : Screen("home", "Home", Icons.Rounded.Home)
    data object Skills : Screen("skills", "Skills", Icons.Rounded.Code)
    data object Contact : Screen("contact", "Contact", Icons.Rounded.Mail)
    companion object { val items = listOf(Home, Skills, Contact) }
}
