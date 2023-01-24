package com.artsam.presentation.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.fragment.app.Fragment
import com.artsam.presentation.main.Greeting
import org.koin.androidx.viewmodel.ext.android.viewModel

class PicturesScreenFr : Fragment() {
    private val viewModel: PicturesViewModel by viewModel()
}

@Composable
fun PicturesScreen(
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Greeting("Android")
    }
}