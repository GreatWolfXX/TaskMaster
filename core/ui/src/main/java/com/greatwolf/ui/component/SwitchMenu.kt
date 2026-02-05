package com.greatwolf.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greatwolf.ui.theme.BodyXSmallTextStyleMedium
import com.greatwolf.ui.theme.BodyXSmallTextStyleNormal
import com.greatwolf.ui.theme.Neutral200
import com.greatwolf.ui.theme.Neutral50
import com.greatwolf.ui.theme.Neutral500
import com.greatwolf.ui.theme.Neutral600
import com.greatwolf.ui.theme.Primary300

@Composable
fun SwitchMenu(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val colorBorder = if (isSystemInDarkTheme()) Neutral600 else Neutral500
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(
                color = if (selected) Primary300 else Color.Transparent,
                shape = RoundedCornerShape(20.dp)
            )
            .border(
                width = if (selected) 0.dp else 1.dp,
                color = if (selected) Color.Transparent else colorBorder,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable(onClick = onClick)
    ) {
        val colorText = if (isSystemInDarkTheme()) Neutral200 else Neutral500
        val colorTextSelected = Neutral50

        Text(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
            text = text,
            style = if (selected) BodyXSmallTextStyleMedium else BodyXSmallTextStyleNormal,
            color = if (selected) colorTextSelected else colorText
        )
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun SwitchMenuPreview() {
    SwitchMenu(
        text = "All Task",
        selected = false
    ) { }
}