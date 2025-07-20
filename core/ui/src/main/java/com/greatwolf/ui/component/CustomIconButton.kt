package com.greatwolf.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greatwolf.ui.R
import com.greatwolf.ui.theme.Neutral0
import com.greatwolf.ui.theme.Neutral200
import com.greatwolf.ui.theme.Neutral50
import com.greatwolf.ui.theme.Neutral700
import com.greatwolf.ui.theme.Neutral900

@Composable
fun CustomIconButton(
    icon: ImageVector,
    enabled: Boolean = true,
    onClick: () -> Unit
) {

    val interactionSource = remember { MutableInteractionSource() }

    val containerColor = if (isSystemInDarkTheme()) Neutral900 else Neutral0
    val contentColor = if (isSystemInDarkTheme()) Neutral50 else Neutral700

    val rippleColor = Neutral200

    val shape = RoundedCornerShape(4.dp)

    Surface(
        modifier = Modifier
            .semantics { role = Role.Button }
            .clip(shape)
            .clickable(
                interactionSource = interactionSource,
                indication = ripple(color = rippleColor),
                enabled = enabled,
                onClick = onClick
            ),
        shape = shape,
        color = containerColor,
        contentColor = contentColor
    ) {
        Icon(
            modifier = Modifier
                .padding(8.dp)
                .size(24.dp),
            imageVector = icon,
            contentDescription = null
        )
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun CustomIconButtonPreview() {
    CustomIconButton(
        icon = ImageVector.vectorResource(R.drawable.ic_eye)
    ) { }
}