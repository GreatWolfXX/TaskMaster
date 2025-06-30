package com.greatwolf.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greatwolf.ui.theme.Neutral50
import com.greatwolf.ui.theme.Primary200

@Composable
fun LoadingBar(
    percentage: Float
) {
    Box(
        modifier = Modifier
            .height(4.dp)
            .width(226.dp)
            .background(
                color = Neutral50,
                shape = RoundedCornerShape(4.dp)
            )
    ) {
        Box(
            modifier = Modifier
                .height(4.dp)
                .fillMaxWidth(percentage)
                .background(
                    color = Primary200,
                    shape = RoundedCornerShape(4.dp)
                )
        )
    }
}

@Preview
@Composable
private fun LoadingBarPreview() {
    LoadingBar(
        percentage = 0.5f
    )
}