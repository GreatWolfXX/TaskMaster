package com.greatwolf.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greatwolf.models.Notification
import com.greatwolf.ui.R
import com.greatwolf.ui.theme.BodyXSmallTextStyleNormal
import com.greatwolf.ui.theme.Error0
import com.greatwolf.ui.theme.Error200
import com.greatwolf.ui.theme.Error25
import com.greatwolf.ui.theme.Error300
import com.greatwolf.ui.theme.Neutral0
import com.greatwolf.ui.theme.Neutral200
import com.greatwolf.ui.theme.Neutral25
import com.greatwolf.ui.theme.Neutral300
import com.greatwolf.ui.theme.Neutral50
import com.greatwolf.ui.theme.Neutral500
import com.greatwolf.ui.theme.Neutral700
import com.greatwolf.ui.theme.Neutral800
import com.greatwolf.ui.theme.Primary0
import com.greatwolf.ui.theme.Primary25
import com.greatwolf.ui.theme.Primary300
import com.greatwolf.ui.theme.Success0
import com.greatwolf.ui.theme.Success200
import com.greatwolf.ui.theme.Success25
import com.greatwolf.ui.theme.Success300
import com.greatwolf.ui.theme.Typography
import com.greatwolf.ui.theme.Warning0
import com.greatwolf.ui.theme.Warning200
import com.greatwolf.ui.theme.Warning25
import com.greatwolf.ui.theme.Warning300

@Composable
fun NotificationCard(
    data: Notification
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NotificationIcon(data.type)
            Spacer(modifier = Modifier.size(12.dp))
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier,
                    text = data.date,
                    style = BodyXSmallTextStyleNormal,
                    color = if (isSystemInDarkTheme()) Neutral50 else Neutral700
                )
                Spacer(modifier = Modifier.size(2.dp))
                Text(
                    modifier = Modifier,
                    text = data.description,
                    style = Typography.labelSmall,
                    color = if (isSystemInDarkTheme()) Neutral200 else Neutral500
                )
            }
        }
        Spacer(modifier = Modifier.size(12.dp))
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = if (isSystemInDarkTheme()) Neutral800 else Neutral25
        )
    }
}

@Composable
private fun NotificationIcon(
    type: Int
) {
    val icon = when (type) {
        0 -> R.drawable.ic_setting
        1 -> R.drawable.ic_chart
        2 -> R.drawable.ic_note
        3 -> R.drawable.ic_timer
        4 -> R.drawable.ic_info_circle
        else -> R.drawable.ic_note
    }

    val iconColor = when (type) {
        0 -> if (isSystemInDarkTheme()) Primary25 else Primary300
        1 -> if (isSystemInDarkTheme()) Success25 else Success200
        2 -> if (isSystemInDarkTheme()) Neutral25 else Neutral700
        3 -> if (isSystemInDarkTheme()) Warning25 else Warning200
        4 -> if (isSystemInDarkTheme()) Error25 else Error200
        else -> if (isSystemInDarkTheme()) Neutral25 else Neutral700
    }

    val backgroundColor = when (type) {
        0 -> if (isSystemInDarkTheme()) Primary300 else Primary0
        1 -> if (isSystemInDarkTheme()) Success300 else Success0
        2 -> if (isSystemInDarkTheme()) Neutral300 else Neutral0
        3 -> if (isSystemInDarkTheme()) Warning300 else Warning0
        4 -> if (isSystemInDarkTheme()) Error300 else Error0
        else -> if (isSystemInDarkTheme()) Neutral300 else Neutral0
    }

    Icon(
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(2.dp)
            )
            .padding(8.dp)
            .size(20.dp),
        imageVector = ImageVector.vectorResource(icon),
        contentDescription = null,
        tint = iconColor
    )
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun NotificationCardPreview() {
    val data = Notification(
        id = "0",
        type = 4,
        description = "4 minutes before the client meeting is held",
        date = "8 minutes ago"
    )
    Box(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        NotificationCard(data = data)
    }
}