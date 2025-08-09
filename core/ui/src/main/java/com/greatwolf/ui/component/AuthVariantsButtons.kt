package com.greatwolf.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greatwolf.ui.R
import com.greatwolf.ui.theme.Dark
import com.greatwolf.ui.theme.Light
import com.greatwolf.ui.theme.Neutral50
import com.greatwolf.ui.theme.Neutral900

@Composable
fun AuthVariantsButtons(
    onClickApple: () -> Unit,
    onClickGoogle: () -> Unit,
    onClickFacebook: () -> Unit,
) {
    Row {
        Box(
            modifier = Modifier
                .semantics { role = Role.Button }
                .clip(CircleShape)
                .background(if (isSystemInDarkTheme()) Neutral900 else Neutral50)
                .clickable(onClick = onClickApple)
        ) {
            Icon(
                modifier = Modifier
                    .padding(10.dp)
                    .size(24.dp),
                imageVector = ImageVector.vectorResource(R.drawable.ic_apple),
                contentDescription = null,
                tint = if (isSystemInDarkTheme()) Light else Dark
            )
        }
        Spacer(modifier = Modifier.size(24.dp))
        Box(
            modifier = Modifier
                .semantics { role = Role.Button }
                .clip(CircleShape)
                .background(if (isSystemInDarkTheme()) Neutral900 else Neutral50)
                .clickable(onClick = onClickGoogle)
        ) {
            Icon(
                modifier = Modifier
                    .padding(10.dp)
                    .size(24.dp),
                imageVector = ImageVector.vectorResource(R.drawable.ic_google),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
        Spacer(modifier = Modifier.size(24.dp))
        Box(
            modifier = Modifier
                .semantics { role = Role.Button }
                .clip(CircleShape)
                .background(if (isSystemInDarkTheme()) Neutral900 else Neutral50)
                .clickable(onClick = onClickFacebook)
        ) {
            Icon(
                modifier = Modifier
                    .padding(10.dp)
                    .size(24.dp),
                imageVector = ImageVector.vectorResource(R.drawable.ic_facebook),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun AuthVariantsButtonsPreview() {
    AuthVariantsButtons(
        onClickApple = { },
        onClickGoogle = { },
        onClickFacebook = { }
    )
}
