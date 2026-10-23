package com.habib.portfolio.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val LightScheme = lightColorScheme(
    primary = Color(0xFF4F46E5), onPrimary = Color.White,
    primaryContainer = Color(0xFFE0E0FF), onPrimaryContainer = Color(0xFF14106B),
    secondary = Color(0xFF0E9F8E), onSecondary = Color.White,
    tertiary = Color(0xFFEC4899), onTertiary = Color.White,
    background = Color(0xFFFAF9FF), onBackground = Color(0xFF1B1B21),
    surface = Color(0xFFFAF9FF), onSurface = Color(0xFF1B1B21),
    surfaceVariant = Color(0xFFE6E4F2), onSurfaceVariant = Color(0xFF47464F)
)

val DarkScheme = darkColorScheme(
    primary = Color(0xFFA5A4FF), onPrimary = Color(0xFF1E1A8F),
    primaryContainer = Color(0xFF3A36C4), onPrimaryContainer = Color(0xFFE0E0FF),
    secondary = Color(0xFF5EE0CC), onSecondary = Color(0xFF00382F),
    tertiary = Color(0xFFFFA8D2), onTertiary = Color(0xFF5E1139),
    background = Color(0xFF0F0F14), onBackground = Color(0xFFE5E1E9),
    surface = Color(0xFF0F0F14), onSurface = Color(0xFFE5E1E9),
    surfaceVariant = Color(0xFF2A2A35), onSurfaceVariant = Color(0xFFC9C5D0)
)
