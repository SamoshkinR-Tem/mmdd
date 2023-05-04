package com.artsam.mmdd.module

import androidx.annotation.Keep
import com.artsam.presentation.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@Keep
internal val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}
