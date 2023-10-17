package com.jetbrains.kmpapp.data

import kotlinx.atomicfu.AtomicBoolean
import kotlinx.atomicfu.atomic
import kotlinx.atomicfu.getAndUpdate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class MuseumRepository(
    private val museumApi: MuseumApi,
    private val museumStorage: MuseumStorage,
) {
    private val initialized: AtomicBoolean = atomic(false)
    private suspend fun initIfNeeded() {
        initialized.getAndUpdate { inited ->
            if (!inited)
                museumStorage.saveObjects(museumApi.getData())
            true
        }
    }

    fun getObjects(): Flow<List<MuseumObject>> {
        return flow {
            initIfNeeded()
            emitAll(museumStorage.getObjects())
        }
    }

    fun getObjectById(objectId: Int): Flow<MuseumObject?> {
        return flow {
            initIfNeeded()
            emitAll(museumStorage.getObjectById(objectId))
        }
    }
}
