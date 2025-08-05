package com.greatwolf.ui.component

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.composeuisuite.ohteepee.OhTeePeeDefaults
import com.composeuisuite.ohteepee.OhTeePeeInput
import com.greatwolf.ui.R
import com.greatwolf.ui.constant.OTP_CODE_LENGTH
import com.greatwolf.ui.constant.RESEND_OTP_DELAY_SECONDS
import com.greatwolf.ui.constant.TERMS_TAG
import com.greatwolf.ui.theme.Dark
import com.greatwolf.ui.theme.Error0
import com.greatwolf.ui.theme.Error100
import com.greatwolf.ui.theme.Error25
import com.greatwolf.ui.theme.Error300
import com.greatwolf.ui.theme.Error50
import com.greatwolf.ui.theme.Light
import com.greatwolf.ui.theme.Neutral100
import com.greatwolf.ui.theme.Neutral200
import com.greatwolf.ui.theme.Neutral50
import com.greatwolf.ui.theme.Neutral500
import com.greatwolf.ui.theme.Neutral600
import com.greatwolf.ui.theme.Neutral700
import com.greatwolf.ui.theme.Primary200
import com.greatwolf.ui.theme.Primary300
import com.greatwolf.ui.theme.Primary50
import com.greatwolf.ui.theme.Primary600
import com.greatwolf.ui.theme.Success200
import com.greatwolf.ui.theme.Success25
import com.greatwolf.ui.theme.Success300
import com.greatwolf.ui.theme.Success50
import com.greatwolf.ui.theme.Typography
import kotlinx.coroutines.delay

@Composable
fun OtpForm(
    value: String,
    isError: Boolean = false,
    onValueChange: (String, Boolean) -> Unit,
    onClickResend: () -> Unit,
) {
    var resentDelay by remember { mutableIntStateOf(RESEND_OTP_DELAY_SECONDS) }
    var isSentCode by remember { mutableStateOf(false) }
    val clickableTextColor = if (isSystemInDarkTheme()) Primary300 else Primary200

    LaunchedEffect(isSentCode) {
        if (isSentCode) {
            repeat(RESEND_OTP_DELAY_SECONDS) {
                delay(1000L)
                resentDelay = RESEND_OTP_DELAY_SECONDS - (it + 1)
            }
            isSentCode = false
            resentDelay = RESEND_OTP_DELAY_SECONDS
        }
    }

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
                linkInteractionListener = {
                    isSentCode = true
                    onClickResend()
                }
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

    val activeCellConfig = defaultCellConfig.copy(
        borderColor = if (isSystemInDarkTheme()) Primary600 else Primary50,
        textStyle = Typography.headlineLarge.copy(
            color = Primary300
        )
    )

    val errorCellConfig = defaultCellConfig.copy(
        borderColor = Error50
    )


    val cellConfig = OhTeePeeDefaults.inputConfiguration(
        cellsCount = OTP_CODE_LENGTH,
        emptyCellConfig = defaultCellConfig,
        activeCellConfig = activeCellConfig,
        errorCellConfig = errorCellConfig,
        cellModifier = Modifier
            .height(48.dp)
            .width(56.dp),
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
            value = value,
            onValueChange = onValueChange,
            autoFocusByDefault = false,
            isValueInvalid = isError,
            configurations = cellConfig,
        )
        Spacer(modifier = Modifier.size(12.dp))
        if (isError) {
            Spacer(modifier = Modifier.size(12.dp))
            OtpStatus(
                text = stringResource(R.string.otp_code_is_wrong),
                isSuccess = false
            )
        } else {
            val resentText = buildAnnotatedString {
                append(stringResource(R.string.resend_otp, resentDelay))
            }

            Text(
                text = if (isSentCode) resentText else annotatedString,
                style = Typography.labelSmall,
                color = if (isSystemInDarkTheme()) Neutral200 else Neutral500
            )
            Spacer(modifier = Modifier.size(12.dp))
            AnimatedVisibility(isSentCode) {
                OtpStatus(
                    text = stringResource(R.string.code_sent),
                    isSuccess = true
                )
            }
        }
    }
}

@Composable
private fun OtpStatus(
    text: String,
    isSuccess: Boolean
) {
    val textSuccessColor = if (isSystemInDarkTheme()) Success25 else Success200
    val textErrorColor = if (isSystemInDarkTheme()) Error25 else Error100
    val bgSuccessColor = if (isSystemInDarkTheme()) Success300 else Success50
    val bgErrorColor = if (isSystemInDarkTheme()) Error300 else Error0
    Box(
        modifier = Modifier
            .padding(3.5.dp)
            .background(
                shape = RoundedCornerShape(4.dp),
                color = if (isSuccess) bgSuccessColor else bgErrorColor
            )
    ) {
        Text(
            modifier = Modifier
                .padding(10.dp),
            text = text,
            style = Typography.labelSmall,
            color = if (isSuccess) textSuccessColor else textErrorColor
        )
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun OtpFormPreview() {
    OtpForm(
        value = "24",
        isError = false,
        onValueChange = { _, _ -> },
        onClickResend = {}
    )
}
