package com.jetbrains.kmpapp.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.KoinApplication
import org.koin.core.annotation.Module
import org.koin.core.annotation.Singleton

/**
 * Main Koin dependency injection module for the application.
 *
 * This module is responsible for providing all dependencies used throughout the app.
 * It uses Koin's annotation-based configuration with:
 * - [@Module][Module]: Marks this class as a Koin module
 * - [@ComponentScan][ComponentScan]: Automatically scans and registers all annotated components
 *   under the `com.jetbrains.kmpapp` package
 * - [@Configuration][Configuration]: Indicates this is the root configuration module
 */
@Module
@ComponentScan("com.jetbrains.kmpapp")
@Configuration
class AppModule {

    /**
     * Provides a configured [HttpClient] instance as a singleton.
     *
     * The client is configured with:
     * - JSON serialization using kotlinx.serialization
     * - `ignoreUnknownKeys = true` to gracefully handle API responses with extra fields
     * - Content negotiation set to accept any content type (workaround for API not serving `application/json`)
     *
     * @return A configured [HttpClient] instance for making network requests
     */
    @Singleton
    fun httpClient(): HttpClient {
        val json = Json { ignoreUnknownKeys = true }
        return HttpClient {
            install(ContentNegotiation) {
                // TODO Fix API so it serves application/json
                json(json, contentType = ContentType.Any)
            }
        }
    }
}

/**
 * Koin application entry point.
 *
 * This object is annotated with [@KoinApplication][KoinApplication] to generate
 * the necessary Koin startup code at compile time using KSP (Kotlin Symbol Processing).
 */
@KoinApplication
object KoinApp
