package com.greatwolf.taskmaster.ui.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import com.greatwolf.taskmaster.ui.theme.Neutral50
import com.greatwolf.taskmaster.ui.theme.Neutral700
import com.greatwolf.taskmaster.ui.theme.Primary200
import kotlin.math.absoluteValue

private const val SLIDER_INDICATOR_SIZE = 3

@Composable
fun SliderIndicator(
    modifier: Modifier = Modifier,
    pagerState: PagerState
) {
    val currentPage = pagerState.currentPage

    val standardWidth = 12.dp
    val activeWidth = 48.dp

    Row(
        modifier = modifier
            .wrapContentWidth(),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        repeat(SLIDER_INDICATOR_SIZE) { index ->
            val isActive = currentPage == index

            val pageOffset = (pagerState.currentPage + pagerState.currentPageOffsetFraction) - index
            val progress = 1f - pageOffset.absoluteValue.coerceIn(0f, 1f)

            val width by animateDpAsState(
                targetValue = lerp(standardWidth, activeWidth, progress)
            )

            val indicatorColor = if (isActive) {
                Primary200
            } else {
                if (isSystemInDarkTheme()) Neutral700 else Neutral50
            }

            Box(
                modifier = Modifier
                    .height(4.dp)
                    .width(width)
                    .background(
                        color = indicatorColor,
                        shape = RoundedCornerShape(4.dp)
                    )
            )
        }
    }
}

@Preview
@Composable
fun SliderIndicatorPreview() {
    val pagerState = rememberPagerState(
        pageCount = { 4 }
    )

    SliderIndicator(
        pagerState = pagerState
    )
}