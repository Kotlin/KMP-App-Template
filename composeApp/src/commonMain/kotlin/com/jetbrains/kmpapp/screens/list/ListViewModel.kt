package com.jetbrains.kmpapp.screens.list

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.jetbrains.kmpapp.data.MuseumApi
import com.jetbrains.kmpapp.data.MuseumObject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class ListViewModel(museumApi: MuseumApi) : ScreenModel {
    val objects: StateFlow<List<MuseumObject>> =
        museumApi.data
            .stateIn(coroutineScope, SharingStarted.WhileSubscribed(5000), emptyList())
}
