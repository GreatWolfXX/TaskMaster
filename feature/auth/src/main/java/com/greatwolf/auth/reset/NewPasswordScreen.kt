package com.greatwolf.auth.reset

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.greatwolf.auth.R
import com.greatwolf.ui.component.CustomButton
import com.greatwolf.ui.component.CustomButtonSize
import com.greatwolf.ui.component.CustomButtonType
import com.greatwolf.ui.component.CustomIconButton
import com.greatwolf.ui.component.CustomTextField
import com.greatwolf.ui.component.CustomTextFieldType
import com.greatwolf.ui.provider.LocalSnackbarHostState
import com.greatwolf.ui.theme.Neutral200
import com.greatwolf.ui.theme.Neutral50
import com.greatwolf.ui.theme.Neutral500
import com.greatwolf.ui.theme.Neutral700
import com.greatwolf.ui.theme.Typography
import org.koin.androidx.compose.koinViewModel

@Composable
fun NewPasswordScreen(
    vm: NewPasswordViewModel = koinViewModel()
) {
    val state by vm.state.collectAsStateWithLifecycle()
    val event by vm.event.collectAsStateWithLifecycle(NewPasswordEvent.Idle)

    LaunchedEffect(event) {
        when (event) {
            NewPasswordEvent.Idle -> {}
            NewPasswordEvent.Submit -> {

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

    NewPasswordContent(
        state = state,
        onIntent = { intent ->
            vm.onIntent(intent)
        }
    )
}

@Composable
private fun NewPasswordContent(
    state: NewPasswordUiState,
    onIntent: (NewPasswordIntent) -> Unit
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
            text = stringResource(R.string.create_new_password),
            style = Typography.headlineMedium,
            color = if (isSystemInDarkTheme()) Neutral50 else Neutral700
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.create_new_password_desc),
            style = Typography.labelSmall,
            color = if (isSystemInDarkTheme()) Neutral200 else Neutral500
        )
        Spacer(modifier = Modifier.size(24.dp))
        CustomTextField(
            type = CustomTextFieldType.PASSWORD,
            placeholder = stringResource(R.string.password),
            value = state.password,
            onValueChanged = { value ->
                onIntent(NewPasswordIntent.EnterPassword(value))
            },
            isError = state.passwordError != null,
            hint = state.passwordError?.asString().orEmpty(),
            imeAction = ImeAction.Next
        )
        Spacer(modifier = Modifier.size(16.dp))
        CustomTextField(
            type = CustomTextFieldType.PASSWORD,
            placeholder = stringResource(R.string.confirm_password),
            value = state.passwordRepeat,
            onValueChanged = { value ->
                onIntent(NewPasswordIntent.EnterRepeatPassword(value))
            },
            isError = state.passwordRepeatError != null,
            hint = state.passwordRepeatError?.asString().orEmpty()
        )
        Spacer(modifier = Modifier.size(24.dp))
        CustomButton(
            modifier = Modifier.fillMaxWidth(),
            type = CustomButtonType.PRIMARY,
            size = CustomButtonSize.SMALL,
            text = stringResource(R.string.reset_password)
        ) {
            onIntent(NewPasswordIntent.Submit)
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

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun NewPasswordScreenPreview() {
    val state = NewPasswordUiState()
    NewPasswordContent(
        state = state,
        onIntent = {}
    )
}