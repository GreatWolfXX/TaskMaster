package com.greatwolf.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greatwolf.ui.theme.Neutral100
import com.greatwolf.ui.theme.Neutral200
import com.greatwolf.ui.theme.Neutral500
import com.greatwolf.ui.theme.Neutral600
import com.greatwolf.ui.theme.Typography

@Composable
fun DividerWithText(
    text: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier
                .weight(0.35f),
            thickness = 1.dp,
            color = if (isSystemInDarkTheme()) Neutral600 else Neutral100
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(0.3f),
            text = text,
            style = Typography.labelSmall,
            textAlign = TextAlign.Center,
            color = if (isSystemInDarkTheme()) Neutral200 else Neutral500
        )
        HorizontalDivider(
            modifier = Modifier
                .weight(0.35f),
            thickness = 1.dp,
            color = if (isSystemInDarkTheme()) Neutral600 else Neutral100
        )
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun DividerWithTextPreview() {
    DividerWithText("Test")
}