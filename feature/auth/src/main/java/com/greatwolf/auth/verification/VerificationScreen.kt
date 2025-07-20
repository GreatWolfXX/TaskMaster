package com.greatwolf.auth.verification

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.composeuisuite.ohteepee.OhTeePeeDefaults
import com.composeuisuite.ohteepee.OhTeePeeInput
import com.greatwolf.auth.R
import com.greatwolf.ui.component.CustomButton
import com.greatwolf.ui.component.CustomButtonSize
import com.greatwolf.ui.component.CustomButtonType
import com.greatwolf.ui.component.CustomIconButton
import com.greatwolf.ui.constant.TERMS_TAG
import com.greatwolf.ui.provider.LocalSnackbarHostState
import com.greatwolf.ui.theme.Dark
import com.greatwolf.ui.theme.Light
import com.greatwolf.ui.theme.Neutral100
import com.greatwolf.ui.theme.Neutral200
import com.greatwolf.ui.theme.Neutral50
import com.greatwolf.ui.theme.Neutral500
import com.greatwolf.ui.theme.Neutral600
import com.greatwolf.ui.theme.Neutral700
import com.greatwolf.ui.theme.Primary200
import com.greatwolf.ui.theme.Primary300
import com.greatwolf.ui.theme.Primary600
import com.greatwolf.ui.theme.Typography
import org.koin.androidx.compose.koinViewModel

@Composable
fun VerificationScreen(
    vm: VerificationViewModel = koinViewModel()
) {
    val state by vm.state.collectAsStateWithLifecycle()
    val event by vm.event.collectAsStateWithLifecycle(VerificationEvent.Idle)

    LaunchedEffect(event) {
        when (event) {
            VerificationEvent.Idle -> {}
            VerificationEvent.Submit -> {

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
        onIntent = { intent ->
            vm.onIntent(intent)
        }
    )
}

@Composable
private fun VerificationContent(
    state: VerificationUiState,
    onIntent: (VerificationIntent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(bottom = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(24.dp))
        TopMenu(
            onClickBack = { },
            onClickInfo = { }
        )
        Spacer(modifier = Modifier.size(20.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.verification_title),
            style = Typography.headlineMedium,
            color = if (isSystemInDarkTheme()) Neutral50 else Neutral700
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.verification_desc),
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
                state = state,
                onIntent = onIntent,
                onClickResend = { }
            )
            CustomButton(
                modifier = Modifier.fillMaxWidth(),
                type = CustomButtonType.PRIMARY,
                size = CustomButtonSize.SMALL,
                text = stringResource(R.string.verification)
            ) { }
        }
    }
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

@Composable
private fun OtpForm(
    state: VerificationUiState,
    onIntent: (VerificationIntent) -> Unit,
    onClickResend: () -> Unit
) {
    val clickableTextColor = if (isSystemInDarkTheme()) Primary300 else Primary200

    val annotatedString = buildAnnotatedString {
        append(stringResource(R.string.didnt_receive_otp))
        append(" ")
        withLink(
            link = LinkAnnotation.Clickable(
                tag = TERMS_TAG,
                styles = TextLinkStyles(
                    style = SpanStyle(
                        fontWeight = Typography.labelMedium.fontWeight,
                        color = clickableTextColor
                    ),
                    pressedStyle = SpanStyle(background = Primary600)
                ),
                linkInteractionListener = { onClickResend() }
            )
        ) {
            append(stringResource(R.string.resend))
        }
    }

    val defaultCellConfig = OhTeePeeDefaults.cellConfiguration(
        backgroundColor = if (isSystemInDarkTheme()) Dark else Light,
        borderColor = if (isSystemInDarkTheme()) Neutral100 else Neutral600,
        borderWidth = 1.dp,
        shape = RoundedCornerShape(4.dp),
        textStyle = Typography.headlineLarge.copy(
            color = if (isSystemInDarkTheme()) Neutral50 else Neutral700
        ),
        placeHolderTextStyle = Typography.displayMedium.copy(
            color = if (isSystemInDarkTheme()) Neutral200 else Neutral500,
            fontWeight = FontWeight.Light
        )
    )

    val cellConfig = OhTeePeeDefaults.inputConfiguration(
        cellsCount = 4,
        emptyCellConfig = defaultCellConfig,
        cellModifier = Modifier
            .height(60.dp)
            .width(68.dp),
        placeHolder = stringResource(R.string.long_dash)
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.otp_verification),
            style = Typography.labelMedium,
            color = if (isSystemInDarkTheme()) Neutral200 else Neutral500
        )
        Spacer(modifier = Modifier.size(20.dp))
        OhTeePeeInput(
            value = state.otpValue,
            onValueChange = { value, _ ->
                onIntent.invoke(VerificationIntent.EnterOtp(value))
            },
            autoFocusByDefault = false,
            configurations = cellConfig,
        )
        Spacer(modifier = Modifier.size(12.dp))
        Text(
            text = annotatedString,
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
private fun VerificationScreenPreview() {
    val state = VerificationUiState(
        email = "test@gmail.com"
    )
    VerificationContent(
        state = state,
        onIntent = {}
    )
}