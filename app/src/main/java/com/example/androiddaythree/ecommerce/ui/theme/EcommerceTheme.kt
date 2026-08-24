package com.example.androiddaythree.ecommerce.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val ShoeYellow = Color(0xFFFFD700)
val ShoeBlack = Color(0xFF1A1A1A)
val ShoeGrey = Color(0xFF9E9E9E)
val LightGrey = Color(0xFFF5F5F5)

private val LightColorScheme = lightColorScheme(
    primary = ShoeYellow,
    onPrimary = Color.Black,
    secondary = ShoeBlack,
    onSecondary = Color.White,
    background = Color.White,
    surface = Color.White,
    onBackground = ShoeBlack,
    onSurface = ShoeBlack
)

private val DarkColorScheme = darkColorScheme(
    primary = ShoeYellow,
    onPrimary = Color.Black,
    secondary = ShoeBlack,
    onSecondary = Color.White,
    background = Color.Black,
    surface = ShoeBlack,
    onBackground = Color.White,
    onSurface = Color.White
)

@Composable
fun EcommerceTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        content = content
    )
}
