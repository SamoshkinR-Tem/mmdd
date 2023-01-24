package com.artsam.mmdd.di

import androidx.annotation.Keep
import com.artsam.presentation.home.PicturesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@Keep
val viewModelModule = module {
    viewModel { PicturesViewModel() }
}
