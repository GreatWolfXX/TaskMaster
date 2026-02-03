package com.greatwolf.home

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.greatwolf.ui.component.CustomIconButton
import com.greatwolf.ui.theme.BodyXSmallTextStyleNormal
import com.greatwolf.ui.theme.Neutral200
import com.greatwolf.ui.theme.Neutral50
import com.greatwolf.ui.theme.Neutral500
import com.greatwolf.ui.theme.Neutral700
import com.greatwolf.ui.theme.Typography
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    vm: HomeViewModel = koinViewModel(),
) {
    val state by vm.state.collectAsStateWithLifecycle()
    val event by vm.event.collectAsStateWithLifecycle(HomeEvent.Idle)

    HomeContent(
        state = state,
        onIntent = { intent ->
            vm.onIntent(intent)
        }
    )
}

@Composable
private fun HomeContent(
    state: HomeUiState,
    onIntent: (HomeIntent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WelcomeBlock(
            name = "Artem", //TEST
            onClickNotification = {}
        )
    }
}

@Composable
private fun WelcomeBlock(
    name: String,
    onClickNotification: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            Modifier.wrapContentSize()
        ) {
            Text(
                modifier = Modifier,
                text = stringResource(R.string.welcome_back, name),
                style = BodyXSmallTextStyleNormal,
                color = if (isSystemInDarkTheme()) Neutral200 else Neutral500
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                modifier = Modifier,
                text = stringResource(R.string.ready_conquer_day),
                style = Typography.labelMedium,
                color = if (isSystemInDarkTheme()) Neutral50 else Neutral700
            )
        }
        CustomIconButton(
            icon = ImageVector.vectorResource(com.greatwolf.ui.R.drawable.ic_notification),
            onClick = onClickNotification
        )
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun HomeScreenPreview() {
    val state = HomeUiState()
    HomeContent(
        state = state,
        onIntent = { },
    )
}