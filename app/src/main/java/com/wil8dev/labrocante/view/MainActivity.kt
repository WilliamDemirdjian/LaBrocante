package com.wil8dev.labrocante.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.wil8dev.labrocante.view.core.compose.navigation.LaBrocanteNavigation
import com.wil8dev.labrocante.view.core.compose.theme.LaBrocanteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?,
    ) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            LaBrocanteTheme {
                LaBrocanteNavigation()
            }
        }
    }
}
