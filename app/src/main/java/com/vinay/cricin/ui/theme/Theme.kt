package com.vinay.cricin.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color.Blue,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = Color(0xFF17162E),
    onBackground = Color.White,
    onSurface = Color.White,
    surface = Color(0xFF303030)
)

private val LightColorScheme = lightColorScheme(
    primary = Color.Blue,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = Color(0xFF17162E),
    onBackground = Color.White,
    onSurface = Color.White,
    surface = Color(0xFF303030)

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun CricInTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}