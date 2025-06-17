package com.greatwolf.ui.util

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.compositionLocalOf

val LocalSnackbarHostState = compositionLocalOf<SnackbarHostState> {
    error(ERROR_SNACKBAR_HOST_STATE_PROVIDE)
}
