package com.greatwolf.auth.success

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.greatwolf.auth.R
import com.greatwolf.ui.component.AppLogo
import com.greatwolf.ui.component.CustomButton
import com.greatwolf.ui.component.CustomButtonSize
import com.greatwolf.ui.component.CustomButtonType
import com.greatwolf.ui.provider.LocalSnackbarHostState
import com.greatwolf.ui.theme.Neutral200
import com.greatwolf.ui.theme.Neutral50
import com.greatwolf.ui.theme.Neutral500
import com.greatwolf.ui.theme.Neutral700
import com.greatwolf.ui.theme.Typography
import org.koin.androidx.compose.koinViewModel

@Composable
fun SuccessScreen(
    vm: SuccessViewModel = koinViewModel(),
    title: String,
    desc: String,
    btnText: String,
    navigate: () -> Unit
) {
    val state by vm.state.collectAsStateWithLifecycle()
    val event by vm.event.collectAsStateWithLifecycle(SuccessEvent.Idle)

    LaunchedEffect(event) {
        when (event) {
            SuccessEvent.Idle -> {}
            SuccessEvent.Submit -> {
                navigate()
            }
        }
    }

    val snackbar = LocalSnackbarHostState.current
    val snackbarMessage = state.snackbarMessage?.asString().orEmpty()

    LaunchedEffect(state.snackbarMessage) {
        if (state.snackbarMessage != null) {
            snackbar.showSnackbar(snackbarMessage, duration = SnackbarDuration.Short)
        }
    }

    SuccessContent(
        state = state,
        title = title,
        desc = desc,
        btnText = btnText,
        onIntent = { intent ->
            vm.onIntent(intent)
        }
    )
}

@Composable
private fun SuccessContent(
    state: SuccessUiState,
    title: String,
    desc: String,
    btnText: String,
    onIntent: (SuccessIntent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 24.dp, bottom = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        AppLogo(isVertical = false)
        SuccessLogoWithText(
            title = title,
            desc = desc
        )
        CustomButton(
            modifier = Modifier.fillMaxWidth(),
            type = CustomButtonType.PRIMARY,
            size = CustomButtonSize.SMALL,
            text = btnText
        ) {
            onIntent(SuccessIntent.Submit)
        }
    }
}

@Composable
private fun SuccessLogoWithText(
    title: String,
    desc: String
) {
    val icon =
        if (isSystemInDarkTheme()) R.drawable.ic_success_dark else R.drawable.ic_success_light

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.width(200.dp),
            imageVector = ImageVector.vectorResource(icon),
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(24.dp))
        Text(
            text = title,
            style = Typography.headlineMedium,
            color = if (isSystemInDarkTheme()) Neutral50 else Neutral700
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = desc,
            textAlign = TextAlign.Center,
            style = Typography.labelSmall,
            color = if (isSystemInDarkTheme()) Neutral200 else Neutral500
        )
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun SuccessScreenPreview() {
    val state = SuccessUiState()
    SuccessContent(
        state = state,
        title = "Sign In Successfully!",
        desc = "Congratulations, Jenny! Access granted. Time for productivity!",
        btnText = "Go to Homepage",
        onIntent = {}
    )
}