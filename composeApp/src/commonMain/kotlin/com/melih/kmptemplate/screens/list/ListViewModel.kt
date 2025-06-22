package com.melih.kmptemplate.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melih.kmptemplate.data.MuseumObject
import com.melih.kmptemplate.data.MuseumRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class ListViewModel(museumRepository: MuseumRepository) : ViewModel() {
    val objects: StateFlow<List<MuseumObject>> =
        museumRepository.getObjects()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}
