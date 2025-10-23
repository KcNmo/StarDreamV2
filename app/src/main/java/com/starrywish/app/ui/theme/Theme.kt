package com.starrywish.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColors = darkColorScheme(
    primary = Color(0xFF5E5CE6),
    secondary = Color(0xFF7B7AF7),
    background = Color(0xFF0B1020),
    surface = Color(0xFF12172A),
    onPrimary = Color.White,
)

@Composable
fun StarWishTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColors,
        typography = androidx.compose.material3.Typography(),
        content = content
    )
}
