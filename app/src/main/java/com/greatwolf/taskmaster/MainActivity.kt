package com.greatwolf.taskmaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.greatwolf.taskmaster.navigation.BasicNavigation
import com.greatwolf.ui.theme.TaskMasterTheme
import com.greatwolf.ui.util.LocalSnackbarHostState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val snackbarHostState = remember { SnackbarHostState() }

            TaskMasterTheme {
                CompositionLocalProvider(
                    values = arrayOf(
                        LocalSnackbarHostState provides snackbarHostState
                    )
                ) {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        BasicNavigation()
                    }
                }
            }
        }
    }
}