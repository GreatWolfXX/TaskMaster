package com.greatwolf.ui.preview.provider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.greatwolf.ui.preview.data.HomePreviewMock

class HomePreviewProvider : PreviewParameterProvider<HomePreviewMock.HomeState> {
    override val values = sequenceOf(HomePreviewMock.HomeState())
}
