/**
 * Koin dependency injection configuration for the KMP Museum app.
 *
 * This file defines the DI modules using Koin 4.x Plugin DSL, which enables
 * constructor injection without explicit factory lambdas.
 */
package com.jetbrains.kmpapp.di

import com.jetbrains.kmpapp.data.InMemoryMuseumStorage
import com.jetbrains.kmpapp.data.KtorMuseumApi
import com.jetbrains.kmpapp.data.MuseumApi
import com.jetbrains.kmpapp.data.MuseumRepository
import com.jetbrains.kmpapp.data.MuseumStorage
import com.jetbrains.kmpapp.screens.detail.DetailViewModel
import com.jetbrains.kmpapp.screens.list.ListViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.plugin.module.dsl.create
import org.koin.plugin.module.dsl.single
import org.koin.plugin.module.dsl.viewModel

/**
 * Data layer module providing network, storage, and repository dependencies.
 *
 * Definitions:
 * - [HttpClient]: Configured Ktor client for API calls (via [buildHttpClient])
 * - [KtorMuseumApi]: Museum API implementation bound to [MuseumApi] interface
 * - [InMemoryMuseumStorage]: In-memory cache bound to [MuseumStorage] interface
 * - [MuseumRepository]: Repository coordinating API and storage
 */
val dataModule = module {
    single { create(::buildHttpClient) }
    single<KtorMuseumApi>() bind MuseumApi::class
    single<InMemoryMuseumStorage>() bind MuseumStorage::class
    single<MuseumRepository>()
}

/**
 * Creates and configures an [HttpClient] for museum API requests.
 *
 * The client is configured with:
 * - JSON content negotiation using kotlinx.serialization
 * - Lenient parsing that ignores unknown keys in API responses
 * - Accepts any content type (workaround for API not serving application/json)
 *
 * @return Configured [HttpClient] instance
 */
fun buildHttpClient(): HttpClient {
    val json = Json { ignoreUnknownKeys = true }
    return HttpClient {
        install(ContentNegotiation) {
            // TODO Fix API so it serves application/json
            json(json, contentType = ContentType.Any)
        }
    }
}

/**
 * ViewModel module for screen-level presentation logic.
 *
 * Definitions:
 * - [ListViewModel]: Handles museum object list screen state
 * - [DetailViewModel]: Handles museum object detail screen state
 */
val viewModelModule = module {
    viewModel<ListViewModel>()
    viewModel<DetailViewModel>()
}

/**
 * Root application module that aggregates all feature modules.
 */
val appModule = module { includes(dataModule,viewModelModule) }

/**
 * Initializes the Koin dependency injection framework.
 *
 * Call this function once at application startup before accessing any injected dependencies.
 */
fun initKoin() {
    startKoin {
        modules(appModule)
    }
}
