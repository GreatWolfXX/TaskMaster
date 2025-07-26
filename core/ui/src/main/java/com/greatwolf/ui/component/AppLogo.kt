package com.greatwolf.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.greatwolf.ui.R
import com.greatwolf.ui.theme.Neutral50
import com.greatwolf.ui.theme.Neutral700
import com.greatwolf.ui.theme.Typography

@Composable
fun AppLogo(
    isVertical: Boolean
) {
    if (isVertical) {
        Column(
            modifier = Modifier.wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoWithText(
                logoWidth = 174.dp,
                spacerSize = 24.dp,
                textStyle = Typography.headlineLarge
            )
        }
    } else {
        Row(
            modifier = Modifier.wrapContentSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LogoWithText(
                logoWidth = 40.dp,
                spacerSize = 10.dp,
                textStyle = Typography.headlineSmall
            )
        }
    }
}

@Composable
private fun LogoWithText(
    logoWidth: Dp,
    spacerSize: Dp,
    textStyle: TextStyle
) {
    Image(
        modifier = Modifier.width(logoWidth),
        imageVector = ImageVector.vectorResource(R.drawable.logo_ic),
        contentDescription = null
    )
    Spacer(modifier = Modifier.size(spacerSize))
    Text(
        text = stringResource(R.string.app_name),
        style = textStyle,
        color = if (isSystemInDarkTheme()) Neutral50 else Neutral700
    )
}