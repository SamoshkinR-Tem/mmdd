package com.artsam.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import com.artsam.presentation.navigation.MukundaNavGraph
import com.google.accompanist.appcompattheme.AppCompatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            /**
             * Use [AppCompatTheme] if you need to use legacy or .xml
             * but it doesn't work with the Compose @Preview("Class contents")
             *
             * To make the preview work - use  [MaterialTheme]
             */
            MaterialTheme {
                MukundaNavGraph()
            }
        }
    }
}
