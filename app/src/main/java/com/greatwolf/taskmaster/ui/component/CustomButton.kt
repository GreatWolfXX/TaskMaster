package com.greatwolf.taskmaster.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.greatwolf.taskmaster.ui.theme.BodyXSmallTextStyleSemiBold
import com.greatwolf.taskmaster.ui.theme.Error100
import com.greatwolf.taskmaster.ui.theme.Error200
import com.greatwolf.taskmaster.ui.theme.Error25
import com.greatwolf.taskmaster.ui.theme.Light
import com.greatwolf.taskmaster.ui.theme.Neutral100
import com.greatwolf.taskmaster.ui.theme.Neutral200
import com.greatwolf.taskmaster.ui.theme.Neutral25
import com.greatwolf.taskmaster.ui.theme.Neutral500
import com.greatwolf.taskmaster.ui.theme.Neutral600
import com.greatwolf.taskmaster.ui.theme.Primary300
import com.greatwolf.taskmaster.ui.theme.Primary50
import com.greatwolf.taskmaster.ui.theme.Primary600
import com.greatwolf.taskmaster.ui.theme.Typography

enum class CustomButtonType {
    PRIMARY,
    SECONDARY,
    TERTIARY,
    DESTRUCTIVE
}

enum class CustomButtonSize(val height: Dp) {
    LARGE(52.dp),
    MEDIUM(48.dp),
    SMALL(40.dp),
    XSMALL(32.dp)
}

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    type: CustomButtonType = CustomButtonType.PRIMARY,
    size: CustomButtonSize = CustomButtonSize.LARGE,
    startIcon: ImageVector? = null,
    endIcon: ImageVector? = null,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {

    val interactionSource = remember { MutableInteractionSource() }

    val border = when (type) {
        CustomButtonType.PRIMARY -> { null }

        CustomButtonType.SECONDARY -> {
            BorderStroke(
                width = 1.dp,
                color = if (isSystemInDarkTheme()) Neutral600 else Neutral100
            )
        }

        CustomButtonType.TERTIARY -> { null }

        CustomButtonType.DESTRUCTIVE -> { null }
    }

    val containerColor = when (type) {
        CustomButtonType.PRIMARY -> { Primary300 }
        CustomButtonType.SECONDARY -> { Color.Transparent }
        CustomButtonType.TERTIARY -> { Color.Transparent }
        CustomButtonType.DESTRUCTIVE -> { Error100 }
    }

    val disabledContainerColor = when (type) {
        CustomButtonType.PRIMARY -> { Primary50 }
        CustomButtonType.SECONDARY -> { Color.Transparent }
        CustomButtonType.TERTIARY -> { Color.Transparent }
        CustomButtonType.DESTRUCTIVE -> { Error25 }
    }

    val contentColor = when (type) {
        CustomButtonType.PRIMARY -> { Light }
        CustomButtonType.SECONDARY -> { Neutral500 }
        CustomButtonType.TERTIARY -> { Neutral200 }
        CustomButtonType.DESTRUCTIVE -> { Light }
    }

    val disabledContentColor = when (type) {
        CustomButtonType.PRIMARY -> { Light }
        CustomButtonType.SECONDARY -> { Neutral200 }
        CustomButtonType.TERTIARY -> { Neutral200 }
        CustomButtonType.DESTRUCTIVE -> { Light }
    }

    val rippleColor = when (type) {
        CustomButtonType.PRIMARY -> { Primary600 }
        CustomButtonType.SECONDARY -> { Neutral25 }
        CustomButtonType.TERTIARY -> { Neutral25 }
        CustomButtonType.DESTRUCTIVE -> { Error200 }
    }

    val textStyle = when (size) {
        CustomButtonSize.LARGE -> { Typography.bodyLarge }
        CustomButtonSize.MEDIUM -> { Typography.bodyLarge }
        CustomButtonSize.SMALL -> { Typography.labelLarge }
        CustomButtonSize.XSMALL -> { BodyXSmallTextStyleSemiBold }
    }
    Surface(
        modifier = modifier
            .height(size.height)
            .wrapContentWidth()
            .semantics { role = Role.Button }
            .clickable(
                interactionSource = interactionSource,
                indication = ripple(color = rippleColor),
                enabled = enabled,
                onClick = onClick
            ),
        shape = RoundedCornerShape(12.dp),
        color = if (enabled) containerColor else disabledContainerColor,
        contentColor = if (enabled) contentColor else disabledContentColor,
        border = border
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (startIcon != null) {
                Icon(
                    modifier = Modifier
                        .size(20.dp)
                        .padding(end = 8.dp),
                    imageVector = startIcon,
                    contentDescription = null
                )
            }
            Text(
                text = text,
                style = textStyle
            )
            if (endIcon != null) {
                Icon(
                    modifier = Modifier
                        .size(20.dp)
                        .padding(start = 8.dp),
                    imageVector = endIcon,
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
fun CustomButtonPreview() {
    CustomButton(
        type = CustomButtonType.PRIMARY,
        size = CustomButtonSize.MEDIUM,
        text = "Button"
    ) { }
}