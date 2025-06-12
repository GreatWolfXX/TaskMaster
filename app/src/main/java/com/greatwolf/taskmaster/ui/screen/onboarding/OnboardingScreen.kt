package com.greatwolf.taskmaster.ui.screen.onboarding

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greatwolf.taskmaster.R
import com.greatwolf.taskmaster.ui.component.CustomButton
import com.greatwolf.taskmaster.ui.component.CustomButtonSize
import com.greatwolf.taskmaster.ui.component.CustomButtonType
import com.greatwolf.taskmaster.ui.component.SliderIndicator
import com.greatwolf.taskmaster.ui.theme.BodyXSmallTextStyleNormal
import com.greatwolf.taskmaster.ui.theme.Dark
import com.greatwolf.taskmaster.ui.theme.Light
import com.greatwolf.taskmaster.ui.theme.Neutral200
import com.greatwolf.taskmaster.ui.theme.Neutral50
import com.greatwolf.taskmaster.ui.theme.Neutral500
import com.greatwolf.taskmaster.ui.theme.Neutral700
import com.greatwolf.taskmaster.ui.theme.Primary0
import com.greatwolf.taskmaster.ui.theme.Primary600
import com.greatwolf.taskmaster.ui.theme.Typography

@Composable
fun OnboardingScreen() {

}

@Composable
private fun PagerScreen(
    onboardingPages: OnboardingPages
) {
    val backgroundImage = if (isSystemInDarkTheme()) Primary600 else Primary0
    val backgroundContent = if (isSystemInDarkTheme()) Dark else Light
    val title = if (isSystemInDarkTheme()) Neutral50 else Neutral700
    val desc = if (isSystemInDarkTheme()) Neutral200 else Neutral500

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundImage)
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 40.dp)
                .padding(bottom = 24.dp),
            painter = painterResource(onboardingPages.image),
            contentDescription = null
        )
        Box(
            modifier = Modifier
                .fillMaxHeight(0.44f)
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .shadow(4.dp)
                .background(backgroundContent)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.size(32.dp))
                    Text(
                        text = stringResource(onboardingPages.title),
                        style = Typography.headlineMedium,
                        textAlign = TextAlign.Center,
                        color = title
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(
                        text = stringResource(onboardingPages.description),
                        style = BodyXSmallTextStyleNormal,
                        textAlign = TextAlign.Center,
                        color = desc
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    SliderIndicator(
                        pageSize = 4,
                        currentPage = 0
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CustomButton(
                        modifier = Modifier.fillMaxWidth(),
                        type = CustomButtonType.PRIMARY,
                        size = CustomButtonSize.MEDIUM,
                        text = stringResource(R.string.next)
                    ) { }
                    Spacer(modifier = Modifier.size(16.dp))
                    CustomButton(
                        modifier = Modifier.fillMaxWidth(),
                        type = CustomButtonType.SECONDARY,
                        size = CustomButtonSize.MEDIUM,
                        text = stringResource(R.string.skip)
                    ) { }
                    Spacer(modifier = Modifier.size(24.dp))
                }
            }
        }
    }
}

@Composable
private fun FullSizePagerScreen(
    onboardingPages: OnboardingPages
) {
    val darkGradientColors = listOf(Color.Transparent, Dark)
    val lightGradientColors = listOf(Color.Transparent, Light)

    val background = if (isSystemInDarkTheme()) darkGradientColors else lightGradientColors
    val title = if (isSystemInDarkTheme()) Neutral50 else Neutral700
    val desc = if (isSystemInDarkTheme()) Neutral200 else Neutral500

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(onboardingPages.image),
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.TopCenter,
            contentDescription = null
        )
        Box(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(
                    brush = Brush.verticalGradient(
                        startY = 0f,
                        endY = 300f,
                        colors = background
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Spacer(modifier = Modifier.size(32.dp))
                Text(
                    text = stringResource(onboardingPages.title),
                    style = Typography.displaySmall,
                    textAlign = TextAlign.Center,
                    color = title
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = stringResource(onboardingPages.description),
                    style = BodyXSmallTextStyleNormal,
                    textAlign = TextAlign.Center,
                    color = desc
                )
                Spacer(modifier = Modifier.size(24.dp))
                CustomButton(
                    modifier = Modifier.fillMaxWidth(),
                    type = CustomButtonType.PRIMARY,
                    size = CustomButtonSize.MEDIUM,
                    text = stringResource(R.string.get_started)
                ) { }
                Spacer(modifier = Modifier.size(24.dp))
            }
        }
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun FirstPagerScreenPreview() {
    PagerScreen(OnboardingPages.First(isSystemInDarkTheme()))
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun SecondPagerScreenPreview() {
    PagerScreen(OnboardingPages.Second(isSystemInDarkTheme()))
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun ThirdPagerScreenPreview() {
    PagerScreen(OnboardingPages.Third(isSystemInDarkTheme()))
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun FourthPagerScreenPreview() {
    FullSizePagerScreen(OnboardingPages.Fourth)
}