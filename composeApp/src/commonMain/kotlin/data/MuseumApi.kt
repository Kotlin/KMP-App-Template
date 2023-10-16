package data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MuseumApi(private val client: HttpClient) {
    companion object {
        private const val API_URL =
            "https://raw.githubusercontent.com/zsmb13/metapi-sample/main/list.json"
    }

    private var objects: List<MuseumObject>? = null

    val data: Flow<List<MuseumObject>>
        get() = flow {
            val currentObjects = objects
            if (currentObjects != null) {
                emit(currentObjects)
            } else {
                val newObjects = client.get(API_URL).body<List<MuseumObject>>()
                objects = newObjects
                emit(newObjects)
            }
        }
}
