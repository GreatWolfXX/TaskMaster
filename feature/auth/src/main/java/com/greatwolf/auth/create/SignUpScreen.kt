package com.greatwolf.auth.create

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greatwolf.auth.R
import com.greatwolf.ui.component.AuthVariantsButtons
import com.greatwolf.ui.component.CustomButton
import com.greatwolf.ui.component.CustomButtonSize
import com.greatwolf.ui.component.CustomButtonType
import com.greatwolf.ui.component.CustomCheckbox
import com.greatwolf.ui.component.CustomTextField
import com.greatwolf.ui.component.CustomTextFieldType
import com.greatwolf.ui.component.DividerWithText
import com.greatwolf.ui.theme.BodyXSmallTextStyleNormal
import com.greatwolf.ui.theme.Neutral0
import com.greatwolf.ui.theme.Neutral200
import com.greatwolf.ui.theme.Neutral500
import com.greatwolf.ui.theme.Neutral700
import com.greatwolf.ui.theme.Primary200
import com.greatwolf.ui.theme.Primary300
import com.greatwolf.ui.theme.Typography
import com.greatwolf.ui.util.PRIVACY_TAG
import com.greatwolf.ui.util.SIGN_IN_TAG
import com.greatwolf.ui.util.TERMS_TAG

@Composable
fun SignUpScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(64.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.create_account_title),
            style = Typography.displaySmall,
            color = if (isSystemInDarkTheme()) Neutral0 else Neutral700
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.create_account_desc),
            style = Typography.labelSmall,
            color = if (isSystemInDarkTheme()) Neutral200 else Neutral500
        )
        Spacer(modifier = Modifier.size(40.dp))
        CustomTextField(
            label = stringResource(R.string.your_email),
            placeholder = stringResource(R.string.email),
            value = "",
            onValueChanged = { },
        )
        Spacer(modifier = Modifier.size(16.dp))
        CustomTextField(
            type = CustomTextFieldType.PASSWORD,
            label = stringResource(R.string.password),
            placeholder = stringResource(R.string.password),
            value = "",
            onValueChanged = { },
        )
        Spacer(modifier = Modifier.size(16.dp))
        CustomTextField(
            type = CustomTextFieldType.PASSWORD,
            label = stringResource(R.string.confirm_password),
            placeholder = stringResource(R.string.confirm_password),
            value = "",
            onValueChanged = { },
        )
        Spacer(modifier = Modifier.size(16.dp))
        TermsAndPrivacyBlock(
            onClickTerms = { },
            onClickPrivacy = { }
        )
        Spacer(modifier = Modifier.size(24.dp))
        CustomButton(
            modifier = Modifier.fillMaxWidth(),
            type = CustomButtonType.PRIMARY,
            size = CustomButtonSize.SMALL,
            text = stringResource(R.string.sign_up)
        ) { }
        Spacer(modifier = Modifier.size(40.dp))
        DividerWithText(
            text = stringResource(R.string.or_sign_up_with)
        )
        Spacer(modifier = Modifier.size(24.dp))
        AuthVariantsButtons(
            onClickApple = { },
            onClickGoogle = { },
            onClickFacebook = { }
        )
        Spacer(modifier = Modifier.size(32.dp))
        AlreadyHaveAccountBlock { }
    }
}

@Composable
private fun TermsAndPrivacyBlock(
    onClickTerms: () -> Unit,
    onClickPrivacy: () -> Unit
) {
    val clickableTextColor = if (isSystemInDarkTheme()) Primary300 else Primary200

    val annotatedString = buildAnnotatedString {
        append(stringResource(R.string.agree_to))
        append(" ")
        withLink(
            link = LinkAnnotation.Clickable(
                tag = TERMS_TAG,
                styles = TextLinkStyles(
                    style = SpanStyle(color = clickableTextColor)
                ),
                linkInteractionListener = { onClickTerms() }
            )
        ) {
            append(stringResource(R.string.terms_and_conditionts))
        }
        append(" ")
        append(stringResource(R.string.and))
        append(" ")
        withLink(
            link = LinkAnnotation.Clickable(
                tag = PRIVACY_TAG,
                styles = TextLinkStyles(
                    style = SpanStyle(color = clickableTextColor)
                ),
                linkInteractionListener = { onClickPrivacy() }
            )
        ) {
            append(stringResource(R.string.privacy_policy))
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomCheckbox(
            checked = false,
            onCheckedChange = { }
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = annotatedString,
            style = BodyXSmallTextStyleNormal,
            color = if (isSystemInDarkTheme()) Neutral200 else Neutral500
        )
    }
}

@Composable
private fun AlreadyHaveAccountBlock(
    onClick: () -> Unit
) {
    val clickableTextColor = if (isSystemInDarkTheme()) Primary300 else Primary200

    val annotatedString = buildAnnotatedString {
        append(stringResource(R.string.already_have_account))
        append(" ")
        withLink(
            link = LinkAnnotation.Clickable(
                tag = SIGN_IN_TAG,
                styles = TextLinkStyles(
                    style = SpanStyle(
                        color = clickableTextColor,
                        fontWeight = Typography.labelMedium.fontWeight
                    )
                ),
                linkInteractionListener = { onClick() }
            )
        ) {
            append(stringResource(R.string.sign_in))
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
private fun SignUpScreenPreview() {
    SignUpScreen()
}