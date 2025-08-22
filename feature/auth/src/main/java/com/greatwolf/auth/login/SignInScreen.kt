package com.greatwolf.auth.login

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.greatwolf.auth.R
import com.greatwolf.ui.component.AuthVariantsButtons
import com.greatwolf.ui.component.CustomButton
import com.greatwolf.ui.component.CustomButtonSize
import com.greatwolf.ui.component.CustomButtonType
import com.greatwolf.ui.component.CustomCheckbox
import com.greatwolf.ui.component.CustomTextField
import com.greatwolf.ui.component.CustomTextFieldType
import com.greatwolf.ui.component.DividerWithText
import com.greatwolf.ui.constant.SIGN_IN_TAG
import com.greatwolf.ui.constant.TERMS_TAG
import com.greatwolf.ui.provider.LocalSnackbarHostState
import com.greatwolf.ui.theme.BodyXSmallTextStyleNormal
import com.greatwolf.ui.theme.Neutral0
import com.greatwolf.ui.theme.Neutral200
import com.greatwolf.ui.theme.Neutral500
import com.greatwolf.ui.theme.Neutral700
import com.greatwolf.ui.theme.Primary200
import com.greatwolf.ui.theme.Primary300
import com.greatwolf.ui.theme.Primary600
import com.greatwolf.ui.theme.Typography
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignInScreen(
    vm: SignInViewModel = koinViewModel()
) {
    val state by vm.state.collectAsStateWithLifecycle()
    val event by vm.event.collectAsStateWithLifecycle(SignInEvent.Idle)

    LaunchedEffect(event) {
        when (event) {
            SignInEvent.Idle -> {}
            SignInEvent.Submit -> {

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

    SignInContent(
        state = state,
        onIntent = { intent ->
            vm.onIntent(intent)
        }
    )
}

@Composable
private fun SignInContent(
    state: SignInUiState,
    onIntent: (SignInIntent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(64.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.sign_in_title),
            style = Typography.displaySmall,
            color = if (isSystemInDarkTheme()) Neutral0 else Neutral700
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.sign_in_desc),
            style = Typography.labelSmall,
            color = if (isSystemInDarkTheme()) Neutral200 else Neutral500
        )
        Spacer(modifier = Modifier.size(40.dp))
        CustomTextField(
            label = stringResource(R.string.your_email),
            placeholder = stringResource(R.string.email),
            value = state.email,
            onValueChanged = { value ->
                onIntent(SignInIntent.EnterEmail(value))
            },
            isError = state.emailError != null,
            hint = state.emailError?.asString().orEmpty(),
            imeAction = ImeAction.Next
        )
        Spacer(modifier = Modifier.size(16.dp))
        CustomTextField(
            type = CustomTextFieldType.PASSWORD,
            label = stringResource(R.string.password),
            placeholder = stringResource(R.string.password),
            value = state.password,
            onValueChanged = { value ->
                onIntent(SignInIntent.EnterPassword(value))
            },
            isError = state.passwordError != null,
            hint = state.passwordError?.asString().orEmpty(),
            imeAction = ImeAction.Next
        )
        Spacer(modifier = Modifier.size(16.dp))
        IsRememberAndForgotPasswordBlock(
            checked = state.isRememberMe,
            onCheckedChange = { value ->
                onIntent(SignInIntent.ChangeIsRememberMe(value))
            },
            onClickForgotPassword = { },
        )
        Spacer(modifier = Modifier.size(24.dp))
        CustomButton(
            modifier = Modifier.fillMaxWidth(),
            type = CustomButtonType.PRIMARY,
            size = CustomButtonSize.SMALL,
            text = stringResource(R.string.sign_up)
        ) {
            onIntent(SignInIntent.Submit)
        }
        Spacer(modifier = Modifier.size(40.dp))
        DividerWithText(
            text = stringResource(R.string.or_sign_in_with)
        )
        Spacer(modifier = Modifier.size(24.dp))
        AuthVariantsButtons(
            onClickApple = { },
            onClickGoogle = { },
            onClickFacebook = { }
        )
        Spacer(modifier = Modifier.size(32.dp))
        DontHaveAccountBlock { }
    }
}

@Composable
private fun IsRememberAndForgotPasswordBlock(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClickForgotPassword: () -> Unit
) {
    val clickableTextColor = if (isSystemInDarkTheme()) Primary300 else Primary200

    val annotatedString = buildAnnotatedString {
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
                linkInteractionListener = { onClickForgotPassword() }
            )
        ) {
            append(stringResource(R.string.forgot_password))
        }
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomCheckbox(
                checked = checked,
                onCheckedChange = onCheckedChange
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = stringResource(R.string.remember_me),
                style = BodyXSmallTextStyleNormal,
                color = if (isSystemInDarkTheme()) Neutral200 else Neutral500
            )
        }
        Text(
            text = annotatedString,
            style = BodyXSmallTextStyleNormal,
            color = if (isSystemInDarkTheme()) Neutral200 else Neutral500
        )
    }
}

@Composable
private fun DontHaveAccountBlock(
    onClick: () -> Unit
) {
    val clickableTextColor = if (isSystemInDarkTheme()) Primary300 else Primary200

    val annotatedString = buildAnnotatedString {
        append(stringResource(R.string.dont_have_account))
        append(" ")
        withLink(
            link = LinkAnnotation.Clickable(
                tag = SIGN_IN_TAG,
                styles = TextLinkStyles(
                    style = SpanStyle(
                        color = clickableTextColor,
                        fontWeight = Typography.labelMedium.fontWeight
                    ),
                    pressedStyle = SpanStyle(background = Primary600)
                ),
                linkInteractionListener = { onClick() }
            )
        ) {
            append(stringResource(R.string.sign_up))
        }
    }

    Text(
        text = annotatedString,
        style = Typography.labelSmall,
        color = if (isSystemInDarkTheme()) Neutral200 else Neutral500
    )
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun SignInScreenPreview() {
    val state = SignInUiState()
    SignInContent(
        state = state,
        onIntent = { }
    )
}