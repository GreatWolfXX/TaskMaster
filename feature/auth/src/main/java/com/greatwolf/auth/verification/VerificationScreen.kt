package com.greatwolf.auth.verification

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
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
import com.greatwolf.auth.R
import com.greatwolf.ui.component.CustomButton
import com.greatwolf.ui.component.CustomButtonSize
import com.greatwolf.ui.component.CustomButtonType
import com.greatwolf.ui.component.CustomIconButton
import com.greatwolf.ui.component.LoadingOverlay
import com.greatwolf.ui.component.OtpForm
import com.greatwolf.ui.provider.LocalSnackbarHostState
import com.greatwolf.ui.theme.Neutral100
import com.greatwolf.ui.theme.Neutral200
import com.greatwolf.ui.theme.Neutral50
import com.greatwolf.ui.theme.Neutral500
import com.greatwolf.ui.theme.Neutral600
import com.greatwolf.ui.theme.Neutral700
import com.greatwolf.ui.theme.Typography
import org.koin.androidx.compose.koinViewModel

@Composable
fun VerificationScreen(
    vm: VerificationViewModel = koinViewModel(),
    isPasswordReset: Boolean = false,
    navigate: (title: String, desc: String, btnText: String) -> Unit,
    navigateBack: () -> Unit
) {
    val state by vm.state.collectAsStateWithLifecycle()
    val event by vm.event.collectAsStateWithLifecycle(VerificationEvent.Idle)

    val title = stringResource(R.string.verificated_successfully)
    val desc = stringResource(R.string.your_email_verified)
    val btnText = stringResource(R.string.go_to_homepage)

    LaunchedEffect(event) {
        when (event) {
            VerificationEvent.Idle -> {}
            VerificationEvent.Submit -> {
                navigate(title, desc, btnText)
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

    VerificationContent(
        state = state,
        isPasswordReset = isPasswordReset,
        onIntent = { intent ->
            vm.onIntent(intent)
        },
        navigateBack = navigateBack
    )
}

@Composable
private fun VerificationContent(
    state: VerificationUiState,
    isPasswordReset: Boolean,
    onIntent: (VerificationIntent) -> Unit,
    navigateBack: () -> Unit
) {
    val title =
        if (isPasswordReset) stringResource(R.string.password_reset_verification_title) else stringResource(
            R.string.verification_title
        )
    val desc =
        if (isPasswordReset) stringResource(R.string.password_reset_verification_desc) else stringResource(
            R.string.verification_desc
        )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp)
            .padding(bottom = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(24.dp))
        TopMenu(
            onClickBack = navigateBack,
            onClickInfo = { }
        )
        Spacer(modifier = Modifier.size(20.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            style = Typography.headlineMedium,
            color = if (isSystemInDarkTheme()) Neutral50 else Neutral700
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = desc,
            style = Typography.labelSmall,
            color = if (isSystemInDarkTheme()) Neutral200 else Neutral500
        )
        Spacer(modifier = Modifier.size(6.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = state.email,
            style = Typography.labelMedium,
            color = if (isSystemInDarkTheme()) Neutral100 else Neutral600
        )
        Spacer(modifier = Modifier.size(104.dp))
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OtpForm(
                value = state.otpValue,
                isError = state.otpError != null,
                onValueChange = { value, _ ->
                    onIntent(VerificationIntent.EnterOtp(value))
                },
                onClickResend = {
                    onIntent(VerificationIntent.Resend)
                }
            )
            CustomButton(
                modifier = Modifier.fillMaxWidth(),
                type = CustomButtonType.PRIMARY,
                size = CustomButtonSize.SMALL,
                text = stringResource(R.string.verification)
            ) {
                onIntent(VerificationIntent.Submit)
            }
        }
    }

    LoadingOverlay(state.loading)
}

@Composable
private fun TopMenu(
    onClickBack: () -> Unit,
    onClickInfo: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CustomIconButton(
            icon = ImageVector.vectorResource(R.drawable.ic_arrow_left),
            onClick = onClickBack
        )
        CustomIconButton(
            icon = ImageVector.vectorResource(R.drawable.ic_info),
            onClick = onClickInfo
        )
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun VerificationScreenPreview() {
    val state = VerificationUiState(
        email = "test@gmail.com"
    )
    VerificationContent(
        state = state,
        isPasswordReset = false,
        onIntent = {},
        navigateBack = {}
    )
}