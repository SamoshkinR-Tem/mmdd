package com.artsam.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artsam.data.entity.Painting
import com.artsam.data.repo.PaintingsRepository
import com.artsam.presentation.ui.home.HomeViewModel.HomeUiState.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val paintingsRepo: PaintingsRepository
) : ViewModel() {

    // Backing property to avoid state updates from other classes
    private val _uiState = MutableStateFlow(Success(emptyList()))

    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            paintingsRepo.fetch()
                // Update View with the latest favorite news
                // Writes to the value property of MutableStateFlow,
                // adding a new element to the flow and updating all
                // of its collectors
                .flowOn(Dispatchers.IO)
                .onStart {}
                .catch {}
                .collect { paintings ->
                    _uiState.value = Success(paintings)
                }
        }
    }

    fun addOneItem(id: String) {
        if (uiState.value is Success) {
            val paintings = (uiState.value as Success).paintings.toMutableList()
            val painting = paintings.filter { it.id == id }
            paintings.add(painting.first())
            _uiState.value = Success(paintings)
        }
    }

    /**
     * Represents different states for the [HomeScreen]
     */
    sealed class HomeUiState {
        data class Success(val paintings: List<Painting>) : HomeUiState()
        data class Error(val exception: Throwable) : HomeUiState()
    }
}