package com.greatwolf.taskmaster.ui.screen

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
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greatwolf.taskmaster.R
import com.greatwolf.taskmaster.ui.component.LoadingBar
import com.greatwolf.taskmaster.ui.theme.BodyXSmallTextStyleNormal
import com.greatwolf.taskmaster.ui.theme.Dark
import com.greatwolf.taskmaster.ui.theme.Light
import com.greatwolf.taskmaster.ui.theme.Neutral200
import com.greatwolf.taskmaster.ui.theme.Neutral50
import com.greatwolf.taskmaster.ui.theme.Neutral500
import com.greatwolf.taskmaster.ui.theme.Neutral700
import com.greatwolf.taskmaster.ui.theme.Typography
import kotlinx.coroutines.delay

@Composable
fun SplashScreen() {
    var targetValue by remember { mutableFloatStateOf(0f) }

    val percentage by animateFloatAsState(
        targetValue = targetValue,
        animationSpec = tween(durationMillis = 3000)
    )

    LaunchedEffect(Unit) {
        targetValue = 1f
        delay(3000L)
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
        LoadingBar(percentage)
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
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}