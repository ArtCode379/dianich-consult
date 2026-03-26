package dianichconsult.service.app.ui.theme

import android.app.Activity
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Primary,
    secondary = Accent,
    tertiary = Accent,
    background = Background,
    surface = Surface,
    onPrimary = OnPrimary,
    onSecondary = OnPrimary,
    onTertiary = OnPrimary,
    onBackground = OnSurface,
    onSurface = OnSurface,
    onSurfaceVariant = MutedText,
    outline = Divider,
    error = ErrorRed,
    surfaceVariant = Surface,
    inverseSurface = OnSurface,
    inverseOnSurface = Background,
)

private val AppShapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
)

@Composable
fun ANCSLTheme(
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        shapes = AppShapes,
        content = content
    )
}
