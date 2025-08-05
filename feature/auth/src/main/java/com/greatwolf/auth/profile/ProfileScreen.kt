package com.greatwolf.auth.profile

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
import com.greatwolf.ui.component.ProfileImage
import com.greatwolf.ui.provider.LocalSnackbarHostState
import com.greatwolf.ui.theme.Neutral50
import com.greatwolf.ui.theme.Neutral700
import com.greatwolf.ui.theme.Typography
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    vm: ProfileViewModel = koinViewModel()
) {
    val state by vm.state.collectAsStateWithLifecycle()
    val event by vm.event.collectAsStateWithLifecycle(ProfileEvent.Idle)

    LaunchedEffect(event) {
        when (event) {
            ProfileEvent.Idle -> {}
            ProfileEvent.Submit -> {

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

    ProfileContent(
        state = state,
        onIntent = { intent ->
            vm.onIntent(intent)
        }
    )
}

@Composable
private fun ProfileContent(
    state: ProfileUiState,
    onIntent: (ProfileIntent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopMenu(
            onClickBack = { }
        )
        Spacer(modifier = Modifier.size(12.dp))
        ProfileImage(imageUrl = state.imageUrl) { }
        Spacer(modifier = Modifier.size(28.dp))
        CustomTextField(
            label = stringResource(R.string.full_name),
            placeholder = stringResource(R.string.full_name),
            value = state.fullName,
            onValueChanged = { value ->
                onIntent(ProfileIntent.EnterFullName(value))
            },
            isError = state.fullNameError != null,
            hint = state.fullNameError?.asString().orEmpty(),
            imeAction = ImeAction.Next
        )
        Spacer(modifier = Modifier.size(12.dp))
        CustomTextField(
            label = stringResource(R.string.username),
            placeholder = stringResource(R.string.username),
            value = state.userName,
            onValueChanged = { value ->
                onIntent(ProfileIntent.EnterFullName(value))
            },
            isError = state.userNameError != null,
            hint = state.userNameError?.asString().orEmpty(),
            imeAction = ImeAction.Next
        )
        Spacer(modifier = Modifier.size(12.dp))
        CustomTextField(
            trailingIcon = ImageVector.vectorResource(R.drawable.mail_ic),
            label = stringResource(R.string.email),
            placeholder = stringResource(R.string.email),
            value = state.email,
            onValueChanged = { value ->
                onIntent(ProfileIntent.EnterFullName(value))
            },
            isError = state.emailError != null,
            hint = state.emailError?.asString().orEmpty(),
            imeAction = ImeAction.Next
        )
        Spacer(modifier = Modifier.size(28.dp))
        CustomButton(
            modifier = Modifier.fillMaxWidth(),
            type = CustomButtonType.PRIMARY,
            size = CustomButtonSize.SMALL,
            text = stringResource(R.string.btn_continue)
        ) {
            onIntent(ProfileIntent.Submit)
        }
        // Next Feature Need Phone Field
        // Next Feature Need Date Field
    }
}

@Composable
private fun TopMenu(
    onClickBack: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomIconButton(
            icon = ImageVector.vectorResource(R.drawable.ic_arrow_left),
            onClick = onClickBack
        )
        Text(
            text = stringResource(R.string.profile_title),
            style = Typography.bodyMedium,
            color = if (isSystemInDarkTheme()) Neutral50 else Neutral700
        )
        Spacer(modifier = Modifier.size(40.dp))
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun ProfileScreenPreview() {
    val state = ProfileUiState()
    ProfileContent(
        state = state,
        onIntent = { }
    )
}