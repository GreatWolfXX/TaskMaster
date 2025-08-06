package com.greatwolf.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greatwolf.ui.theme.Neutral200
import com.greatwolf.ui.theme.Primary0
import com.greatwolf.ui.theme.Primary300

@Composable
fun LoadingOverlay(
    isLoading: Boolean
) {
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Neutral200.copy(alpha = 0.25f))
                .clickable(enabled = false, onClick = { }),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(48.dp),
                color = Primary0,
                strokeWidth = 4.dp,
                trackColor = Primary300
            )
        }
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun LoadingOverlayPreview() {
    LoadingOverlay(
        isLoading = true
    )
}