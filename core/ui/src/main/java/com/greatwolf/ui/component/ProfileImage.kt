package com.greatwolf.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.greatwolf.ui.R
import com.greatwolf.ui.theme.Light
import com.greatwolf.ui.theme.Primary0
import com.greatwolf.ui.theme.Primary300

@Composable
fun ProfileImage(
    imageUrl: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(
                height = 128.dp,
                width = 120.dp
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.BottomEnd
    ) {
        if (imageUrl.isBlank()) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                imageVector = ImageVector.vectorResource(R.drawable.profile_placeholder),
                contentDescription = null
            )
        } else {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize(),
                model = imageUrl,
                contentDescription = null
            )
        }
        ProfileBrushIcon()
    }
}

@Composable
private fun ProfileBrushIcon() {
    val shape = RoundedCornerShape(4.dp)
    Box(
        modifier = Modifier
            .padding(
                end = 4.dp,
                bottom = 8.dp
            )
            .background(
                color = Primary300,
                shape = shape
            )
            .border(
                width = 1.dp,
                color = Primary0,
                shape = shape
            )
            .shadow(
                elevation = 4.dp
            )
    ) {
        Icon(
            modifier = Modifier
                .padding(4.dp)
                .size(20.dp),
            imageVector = ImageVector.vectorResource(R.drawable.brush_ic),
            contentDescription = null,
            tint = Light
        )
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun ProfileImagePreview() {
    ProfileImage(
        imageUrl = "",
        onClick = { }
    )
}