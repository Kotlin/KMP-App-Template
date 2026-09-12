package com.jetbrains.kmpapp.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetbrains.kmpapp.data.MuseumObject
import com.jetbrains.kmpapp.data.MuseumRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

sealed interface ListUiState {
    data object Loading : ListUiState
    data class Error(val message: String) : ListUiState
    data class Success(val objects: List<MuseumObject>) : ListUiState
}

class ListViewModel(private val museumRepository: MuseumRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<ListUiState>(ListUiState.Loading)
    val uiState: StateFlow<ListUiState> = _uiState.asStateFlow()

    init {
        observeObjects()
    }

    private fun observeObjects() {
        viewModelScope.launch {
            museumRepository.getObjects()
                .catch { e ->
                    _uiState.value = ListUiState.Error(e.message ?: "Unknown error")
                }
                .collect { objects ->
                    if (objects.isEmpty()) {
                        refresh()
                    } else {
                        _uiState.value = ListUiState.Success(objects)
                    }
                }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _uiState.value = ListUiState.Loading
            try {
                museumRepository.refresh()
                // Repository refresh will update the storage,
                // which will trigger the collection in observeObjects
            } catch (e: Exception) {
                _uiState.value = ListUiState.Error(e.message ?: "Failed to refresh data")
            }
        }
    }
}
