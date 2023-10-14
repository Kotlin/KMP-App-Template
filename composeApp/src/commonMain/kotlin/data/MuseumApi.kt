import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object MuseumApi {
    private const val API_URL = "https://raw.githubusercontent.com/zsmb13/metapi-sample/main/list.json"

    private val json = Json {
        ignoreUnknownKeys = true
    }
    private val client = HttpClient {
        install(ContentNegotiation) {
            // TODO Fix API so it serves application/json
            json(json, contentType = ContentType.Any)
        }
    }

    suspend fun loadData(): List<MuseumObject> = client.get(API_URL).body()
}
