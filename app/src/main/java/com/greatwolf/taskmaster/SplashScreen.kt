package com.greatwolf.taskmaster

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.greatwolf.taskmaster.navigation.Route
import com.greatwolf.ui.theme.BodyXSmallTextStyleNormal
import com.greatwolf.ui.theme.Dark
import com.greatwolf.ui.theme.Light
import com.greatwolf.ui.theme.Neutral200
import com.greatwolf.ui.theme.Neutral50
import com.greatwolf.ui.theme.Neutral500
import com.greatwolf.ui.theme.Neutral700
import com.greatwolf.ui.theme.Typography
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    vm: SplashViewModel = koinViewModel(),
    navigate: (Route) -> Unit
) {
    val state by vm.state.collectAsStateWithLifecycle()
    val event by vm.event.collectAsStateWithLifecycle(SplashEvent.Idle)

    val percentage by animateFloatAsState(
        targetValue = state.progress,
        animationSpec = tween(durationMillis = 3000)
    )

    LaunchedEffect(event) {
        when (event) {
            SplashEvent.Idle -> {}

            SplashEvent.Finish -> {
                navigate(state.destination)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = if (isSystemInDarkTheme()) Dark else Light
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier)
        LogoWithText()
        LoadingBarWithText(percentage)
    }
}

@Composable
private fun LogoWithText() {
    Column(
        modifier = Modifier
            .wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.width(174.dp),
            imageVector = ImageVector.vectorResource(R.drawable.logo_ic),
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(24.dp))
        Text(
            text = stringResource(R.string.app_name),
            style = Typography.headlineLarge,
            color = if (isSystemInDarkTheme()) Neutral50 else Neutral700
        )
    }
}

@Composable
private fun LoadingBarWithText(
    percentage: Float
) {
    Column(
        modifier = Modifier
            .wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        com.greatwolf.ui.component.LoadingBar(percentage)
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = stringResource(R.string.loading),
            style = BodyXSmallTextStyleNormal,
            color = if (isSystemInDarkTheme()) Neutral200 else Neutral500
        )
        Spacer(modifier = Modifier.size(32.dp))
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun SplashScreenPreview() {
    SplashScreen { }
}