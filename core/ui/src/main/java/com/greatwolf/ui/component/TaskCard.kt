package com.greatwolf.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greatwolf.models.Task
import com.greatwolf.ui.theme.BodyXSmallTextStyleNormal
import com.greatwolf.ui.theme.Neutral100
import com.greatwolf.ui.theme.Neutral200
import com.greatwolf.ui.theme.Neutral25
import com.greatwolf.ui.theme.Neutral500
import com.greatwolf.ui.theme.Neutral600
import com.greatwolf.ui.theme.Neutral800
import com.greatwolf.ui.theme.NoteTextStyle
import com.greatwolf.ui.theme.Typography

@Composable
fun TaskCard(
    data: Task,
    onCheckedChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = if (isSystemInDarkTheme()) Neutral800 else Neutral25,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
            ) {
                Text(
                    modifier = Modifier,
                    text = data.title,
                    style = Typography.labelMedium,
                    color = if (isSystemInDarkTheme()) Neutral100 else Neutral600,
                    textDecoration = if (data.completed) TextDecoration.LineThrough else TextDecoration.None
                )
                Spacer(modifier = Modifier.size(2.dp))
                Text(
                    modifier = Modifier,
                    text = data.description,
                    style = BodyXSmallTextStyleNormal,
                    color = if (isSystemInDarkTheme()) Neutral200 else Neutral500
                )
            }
            CustomCheckbox(
                checked = data.completed,
                onCheckedChange = onCheckedChange
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = if (isSystemInDarkTheme()) Neutral600 else Neutral100
        )
        Spacer(modifier = Modifier.size(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier,
                text = data.date,
                style = NoteTextStyle,
                color = if (isSystemInDarkTheme()) Neutral200 else Neutral500
            )
            Text(
                modifier = Modifier,
                text = data.time,
                style = NoteTextStyle,
                color = if (isSystemInDarkTheme()) Neutral200 else Neutral500
            )
        }
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun TaskCardPreview() {
    val data = Task(
        id = "0",
        title = "Complete Website Redesign",
        description = "Redesign Project",
        completed = true,
        date = "Today",
        time = "2:00 PM"
    )
    Box(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        TaskCard(data = data) {

        }
    }
}