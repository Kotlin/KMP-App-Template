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
import org.koin.plugin.module.dsl.startKoin

// --- 2 module version ---
//@Module
//@ComponentScan("com.jetbrains.kmpapp.data")
//class DataModule {
//
//    @Singleton
//    fun httpClient(): HttpClient {
//        val json = Json { ignoreUnknownKeys = true }
//        return HttpClient {
//            install(ContentNegotiation) {
//                // TODO Fix API so it serves application/json
//                json(json, contentType = ContentType.Any)
//            }
//        }
//    }
//}
//
//@Module(includes = [DataModule::class])
//@ComponentScan("com.jetbrains.kmpapp.screens")
//@Configuration
//class AppModule

// --- 1 module version ---
@Module
@ComponentScan("com.jetbrains.kmpapp")
@Configuration
class AppModule {

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

@KoinApplication
object KoinApp

fun initKoin() {
    startKoin<KoinApp>()
}
