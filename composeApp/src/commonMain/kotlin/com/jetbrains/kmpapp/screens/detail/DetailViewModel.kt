package com.jetbrains.kmpapp.screens.detail

import cafe.adriel.voyager.core.model.ScreenModel
import com.jetbrains.kmpapp.data.KtorMuseumApi
import com.jetbrains.kmpapp.data.MuseumObject
import com.jetbrains.kmpapp.data.MuseumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DetailViewModel(private val museumRepository: MuseumRepository) : ScreenModel {
    fun getObject(objectId: Int): Flow<MuseumObject?> =
        museumRepository.getObjectById(objectId)
}
