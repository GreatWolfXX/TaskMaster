package com.greatwolf.onboarding

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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.greatwolf.onboarding.di.onboardingModule
import com.greatwolf.ui.component.CustomButton
import com.greatwolf.ui.component.CustomButtonSize
import com.greatwolf.ui.component.CustomButtonType
import com.greatwolf.ui.component.SliderIndicator
import com.greatwolf.ui.theme.BodyXSmallTextStyleNormal
import com.greatwolf.ui.theme.Dark
import com.greatwolf.ui.theme.Light
import com.greatwolf.ui.theme.Neutral200
import com.greatwolf.ui.theme.Neutral50
import com.greatwolf.ui.theme.Neutral500
import com.greatwolf.ui.theme.Neutral700
import com.greatwolf.ui.theme.Primary0
import com.greatwolf.ui.theme.Primary600
import com.greatwolf.ui.theme.Typography
import com.greatwolf.ui.util.LocalSnackbarHostState
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinApplicationPreview


@Composable
fun OnboardingScreen(
    vm: OnboardingViewModel = koinViewModel(),
    navigateToHome: () -> Unit
) {
    val snackbarHostState = LocalSnackbarHostState.current
    val snackbarErrorMessage = stringResource(R.string.err_unexpected)

    val event by vm.event.collectAsStateWithLifecycle(OnboardingEvent.Idle)

    val pages = listOf(
        OnboardingPages.First(isSystemInDarkTheme()),
        OnboardingPages.Second(isSystemInDarkTheme()),
        OnboardingPages.Third(isSystemInDarkTheme()),
        OnboardingPages.Fourth
    )
    val pagerState = rememberPagerState(
        pageCount = { pages.size }
    )

    LaunchedEffect(event) {
        when (val currentEvent = event) {
            OnboardingEvent.Idle -> {}

            OnboardingEvent.ShowErrorSnackbar -> {
                snackbarHostState.showSnackbar(
                        message = snackbarErrorMessage,
                        duration = SnackbarDuration.Short
                    )
            }

            is OnboardingEvent.Next -> {
                pagerState.animateScrollToPage(currentEvent.page)
            }

            OnboardingEvent.Finish -> {
                navigateToHome()
            }
        }
    }

    HorizontalPager(
        state = pagerState
    ) { position ->
        val page = pages[position]
        if (position < pagerState.pageCount.dec()) {
            PagerScreen(
                pagerState = pagerState,
                onNext = {
                    vm.onIntent(OnboardingIntent.NextClicked(position.inc()))
                },
                onSkip = {
                    vm.onIntent(OnboardingIntent.FinishClicked)
                },
                onboardingPages = page
            )
        } else {
            FinalPagerScreen(
                onboardingPages = page
            ) {
                vm.onIntent(OnboardingIntent.FinishClicked)
            }
        }
    }
}

@Composable
private fun PagerScreen(
    pagerState: PagerState,
    onNext: () -> Unit,
    onSkip: () -> Unit,
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
                        pagerState = pagerState
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CustomButton(
                        modifier = Modifier.fillMaxWidth(),
                        type = CustomButtonType.PRIMARY,
                        size = CustomButtonSize.MEDIUM,
                        text = stringResource(R.string.next),
                        onClick = onNext
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    CustomButton(
                        modifier = Modifier.fillMaxWidth(),
                        type = CustomButtonType.SECONDARY,
                        size = CustomButtonSize.MEDIUM,
                        text = stringResource(R.string.skip),
                        onClick = onSkip
                    )
                    Spacer(modifier = Modifier.size(24.dp))
                }
            }
        }
    }
}

@Composable
private fun FinalPagerScreen(
    onboardingPages: OnboardingPages,
    onClick: () -> Unit
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
                    text = stringResource(R.string.get_started),
                    onClick = onClick
                )
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
private fun OnboardingScreenPreview() {
    KoinApplicationPreview(application = { modules(onboardingModule) }) {
        OnboardingScreen {}
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun FirstPagerScreenPreview() {
    val pagerState = rememberPagerState(
        pageCount = { 4 }
    )

    PagerScreen(
        pagerState = pagerState,
        onNext = {},
        onSkip = {},
        onboardingPages = OnboardingPages.First(isSystemInDarkTheme())
    )
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun SecondPagerScreenPreview() {
    val pagerState = rememberPagerState(
        pageCount = { 4 }
    )

    PagerScreen(
        pagerState = pagerState,
        onNext = {},
        onSkip = {},
        onboardingPages = OnboardingPages.Second(isSystemInDarkTheme())
    )
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun ThirdPagerScreenPreview() {
    val pagerState = rememberPagerState(
        pageCount = { 4 }
    )

    PagerScreen(
        pagerState = pagerState,
        onNext = {},
        onSkip = {},
        onboardingPages = OnboardingPages.Third(isSystemInDarkTheme())
    )
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun FourthPagerScreenPreview() {
    FinalPagerScreen(OnboardingPages.Fourth) {}
}