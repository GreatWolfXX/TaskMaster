package com.greatwolf.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greatwolf.ui.R
import com.greatwolf.ui.theme.Light
import com.greatwolf.ui.theme.Neutral100
import com.greatwolf.ui.theme.Neutral25
import com.greatwolf.ui.theme.Neutral400
import com.greatwolf.ui.theme.Neutral600
import com.greatwolf.ui.theme.Primary300
import com.greatwolf.ui.theme.Primary600

@Composable
fun CustomCheckbox(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    enabled: Boolean = true,
) {
    val interactionSource = remember { MutableInteractionSource() }

    val shape = RoundedCornerShape(6.dp)

    val backgroundColor = if (enabled) {
        if (checked) Primary300 else Color.Transparent
    } else {
        Neutral25
    }

    val iconColor = if (enabled) {
        if (checked) Light else Color.Transparent
    } else {
        Neutral100
    }

    val borderColor = if (checked || !enabled) {
        Color.Transparent
    } else {
        if (isSystemInDarkTheme()) Neutral600 else Neutral100
    }

    Box(
        modifier = modifier
            .size(20.dp)
            .background(
                color = backgroundColor,
                shape = shape
            )
            .border(
                width = 1.dp,
                shape = shape,
                color = borderColor
            )
            .clip(shape)
            .toggleable(
                value = checked,
                interactionSource = interactionSource,
                indication = ripple(color = if (checked) Primary600 else Neutral400),
                enabled = enabled,
                role = Role.Checkbox,
                onValueChange = onCheckedChange,
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(14.dp),
            imageVector = ImageVector.vectorResource(R.drawable.ic_check),
            tint = iconColor,
            contentDescription = null
        )
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun CustomCheckboxPreview() {
    CustomCheckbox(
        checked = false,
        onCheckedChange = { },
        enabled = true
    )
}