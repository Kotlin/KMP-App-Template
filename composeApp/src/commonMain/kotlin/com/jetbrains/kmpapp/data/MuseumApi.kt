package com.jetbrains.kmpapp.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

interface MuseumApi {
    suspend fun getData(): List<MuseumObject>
}

class KtorMuseumApi(private val client: HttpClient) : MuseumApi {
    companion object {
        private const val API_URL =
            "https://raw.githubusercontent.com/zsmb13/metapi-sample/main/list.json"
    }

    override suspend fun getData(): List<MuseumObject> =
        client.get(API_URL).body()
}
