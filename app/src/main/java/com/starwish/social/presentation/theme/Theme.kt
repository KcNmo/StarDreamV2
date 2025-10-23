package com.starwish.social.presentation.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = StarBlueLight,
    onPrimary = TextPrimary,
    secondary = StarGold,
    onSecondary = SpaceDark,
    tertiary = StarGoldLight,
    onTertiary = SpaceDark,
    background = SpaceDark,
    onBackground = TextPrimary,
    surface = SpaceMedium,
    onSurface = TextPrimary,
    surfaceVariant = SpaceLight,
    onSurfaceVariant = TextSecondary,
    error = Error,
    onError = TextPrimary
)

private val LightColorScheme = lightColorScheme(
    primary = StarBlue,
    onPrimary = TextPrimary,
    secondary = StarGold,
    onSecondary = TextPrimary,
    tertiary = StarGoldLight,
    onTertiary = SpaceDark,
    background = Color(0xFFFFFBFE),
    onBackground = SpaceDark,
    surface = Color(0xFFFFFBFE),
    onSurface = SpaceDark,
    surfaceVariant = Color(0xFFF1F5F9),
    onSurfaceVariant = SpaceMedium,
    error = Error,
    onError = TextPrimary
)

@Composable
fun StarWishTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}