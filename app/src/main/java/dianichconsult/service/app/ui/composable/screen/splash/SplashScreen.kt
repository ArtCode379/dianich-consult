package dianichconsult.service.app.ui.composable.screen.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dianichconsult.service.app.R
import dianichconsult.service.app.ui.viewmodel.ANCSLSplashVM
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: ANCSLSplashVM = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit,
    onNavigateToOnboarding: () -> Unit,
) {
    LaunchedEffect(Unit) {
        delay(1500)
        val isOnboarded = viewModel.onboardedState.value
        if (isOnboarded) {
            onNavigateToHomeScreen()
        } else {
            onNavigateToOnboarding()
        }
    }

    SplashScreenContent(
        modifier = modifier,
        onNavigateToHomeScreen = onNavigateToHomeScreen,
        onNavigateToOnboarding = onNavigateToOnboarding,
    )
}

@Composable
fun SplashScreenContent(
    modifier: Modifier = Modifier,
    onNavigateToHomeScreen: () -> Unit,
    onNavigateToOnboarding: () -> Unit,
) {
    var startAnimation by remember { mutableStateOf(false) }
    var showTitle by remember { mutableStateOf(false) }

    val iconAlpha by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 800),
        label = "icon_alpha"
    )

    val iconScale by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0.8f,
        animationSpec = tween(durationMillis = 800),
        label = "icon_scale"
    )

    val titleAlpha by animateFloatAsState(
        targetValue = if (showTitle) 1f else 0f,
        animationSpec = tween(durationMillis = 600),
        label = "title_alpha"
    )

    LaunchedEffect(Unit) {
        startAnimation = true
        delay(200)
        showTitle = true
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF0F1419),
                        Color(0xFF0F1419),
                        Color(0xFF122A28),
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                )
            ),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon),
                contentDescription = "DIANICH Consult",
                modifier = Modifier
                    .size(120.dp)
                    .alpha(iconAlpha)
                    .scale(iconScale),
                contentScale = ContentScale.Fit,
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "DIANICH Consult",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.alpha(titleAlpha),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "IT Consultancy Excellence",
                color = Color(0xFF00BFA6).copy(alpha = titleAlpha * 0.85f),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.alpha(titleAlpha),
            )
        }
    }
}
