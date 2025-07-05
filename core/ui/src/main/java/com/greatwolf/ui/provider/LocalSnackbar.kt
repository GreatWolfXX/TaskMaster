package com.greatwolf.ui.provider

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.compositionLocalOf
import com.greatwolf.ui.constant.ERROR_SNACKBAR_HOST_STATE_PROVIDE

val LocalSnackbarHostState = compositionLocalOf<SnackbarHostState> {
    error(ERROR_SNACKBAR_HOST_STATE_PROVIDE)
}