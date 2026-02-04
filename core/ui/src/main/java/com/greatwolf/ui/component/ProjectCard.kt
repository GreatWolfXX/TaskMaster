package com.greatwolf.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greatwolf.common.extension.normalize
import com.greatwolf.models.Project
import com.greatwolf.ui.R
import com.greatwolf.ui.theme.BodyXSmallTextStyleMedium
import com.greatwolf.ui.theme.BodyXSmallTextStyleNormal
import com.greatwolf.ui.theme.Error200
import com.greatwolf.ui.theme.Light
import com.greatwolf.ui.theme.Neutral100
import com.greatwolf.ui.theme.Neutral50
import com.greatwolf.ui.theme.Primary200
import com.greatwolf.ui.theme.Success0
import com.greatwolf.ui.theme.Typography
import com.greatwolf.ui.theme.Warning200

@Composable
fun ProjectCard(
    data: Project
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .background(Color(color = data.color))
            .drawBehind(onDraw = { drawDecorBackground() }),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 20.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                PriorityBlock(priority = data.priority)
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_more),
                    tint = Neutral50,
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                modifier = Modifier,
                text = data.title,
                style = Typography.titleMedium,
                color = Neutral50
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                modifier = Modifier,
                text = data.description,
                style = BodyXSmallTextStyleNormal,
                color = Neutral100,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
            Spacer(modifier = Modifier.size(20.dp))
            ProgressBar(progress = data.progress)
            Spacer(modifier = Modifier.size(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(vertical = 1.5.dp)
                            .size(16.dp),
                        imageVector = ImageVector.vectorResource(R.drawable.ic_clock),
                        tint = Neutral50,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        modifier = Modifier,
                        text = data.date,
                        style = BodyXSmallTextStyleNormal,
                        color = Neutral50
                    )
                }
                Text(
                    modifier = Modifier,
                    text = stringResource(R.string.percentage, data.progress),
                    style = BodyXSmallTextStyleNormal,
                    color = Neutral50
                )
            }
        }
    }
}

private fun DrawScope.drawDecorBackground() {
    val firstBoxEndTopPoint = size.width / 20f
    val firstBoxEndBottomPoint = size.width / 1.8f
    val spacing = size.width / 15f
    val secondBoxEndTopPoint = (firstBoxEndTopPoint + spacing) + size.width / 2.6f
    val secondBoxEndBottomPoint = (firstBoxEndBottomPoint + spacing) + size.width / 2.6f

    val color = Success0.copy(alpha = 0.05f)

    val firstBox = Path().apply {
        moveTo(x = 0f, y = 0f)

        lineTo(x = 0f, y = size.height)

        lineTo(x = firstBoxEndBottomPoint, y = size.height)

        lineTo(x = firstBoxEndTopPoint, y = 0f)

        close()
    }

    val secondBox = Path().apply {
        moveTo(x = firstBoxEndTopPoint + spacing, y = 0f)

        lineTo(x = firstBoxEndBottomPoint + spacing, y = size.height)

        lineTo(x = secondBoxEndBottomPoint, y = size.height)

        lineTo(x = secondBoxEndTopPoint, y = 0f)

        close()
    }

    drawPath(
        path = firstBox,
        color = color
    )

    drawPath(
        path = secondBox,
        color = color
    )
}

@Composable
private fun PriorityBlock(
    priority: Int
) {
    val text = when (priority) {
        0 -> R.string.low
        1 -> R.string.medium
        2 -> R.string.high
        else -> R.string.low
    }

    val colorText = when (priority) {
        0 -> Warning200
        1 -> Primary200
        2 -> Error200
        else -> Warning200
    }

    Box(
        modifier = Modifier.background(
            color = Light,
            shape = RoundedCornerShape(4.dp)
        )
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            text = stringResource(text),
            style = BodyXSmallTextStyleMedium,
            color = colorText
        )
    }
}

@Composable
private fun ProgressBar(
    progressBlocksNumber: Int = 10,
    progress: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        repeat(times = progressBlocksNumber) { i ->
            val min = (i * 10f)
            val max = (i.inc() * 10f)
            val percentage = (progress / (progressBlocksNumber * 10f)) * 100f
            val fraction = percentage.coerceIn(0f..max).normalize(min, max)
            Box(
                modifier = Modifier
                    .height(4.dp)
                    .weight(1f)
                    .background(
                        color = Neutral100,
                        shape = RoundedCornerShape(1.dp)
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(fraction)
                        .background(
                            color = Primary200,
                            shape = RoundedCornerShape(1.dp)
                        )
                )
            }
        }
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun ProjectCardPreview() {
    val data = Project(
        id = "0",
        priority = 2,
        title = "E-commerce Platform Redesign - NovaShop",
        description = "Overhauling the user interface design of NovaShop, our e-commerce platform, for a modern and asdsdasdasdsdfsdsdfsdfsdfdsffsdasd",
        progress = 70,
        date = "January 30, 2024",
        color = 0xFF0041AA
    )
    Box(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        ProjectCard(data = data)
    }
}