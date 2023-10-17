package com.jetbrains.kmpapp.screens.detail

import cafe.adriel.voyager.core.model.ScreenModel
import com.jetbrains.kmpapp.data.MuseumApi
import com.jetbrains.kmpapp.data.MuseumObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DetailViewModel(private val museumApi: MuseumApi) : ScreenModel {
    fun getObject(objectId: Int): Flow<MuseumObject?> {
        return museumApi.data.map { list ->
            list.find { it.objectID == objectId }
        }
    }
}
