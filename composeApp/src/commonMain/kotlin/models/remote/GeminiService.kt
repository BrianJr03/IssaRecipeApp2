package models.remote

import models.remote.dto.Request
import models.remote.dto.Response
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.post
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.InternalAPI
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

const val TIMEOUT = 30000L

@OptIn(ExperimentalSerializationApi::class, InternalAPI::class)
class GeminiService {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                explicitNulls = false
                encodeDefaults = true
                ignoreUnknownKeys = true
            })
        }
        install(HttpTimeout) {
            connectTimeoutMillis = TIMEOUT
            socketTimeoutMillis = TIMEOUT
            requestTimeoutMillis = TIMEOUT
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }
    private fun getUrl(key: String) =  "$BASE_URL$ROUTE$key"

    suspend fun generateContent(
        apiKey: String,
        prompt: String
    ): Response {
        return makeApiRequest(getUrl(apiKey)) {
            addText(prompt)
        }
    }

    suspend fun generateContentWithMedia(
        apiKey: String,
        prompt: String,
        images: List<ByteArray>
    ): Response {
        return makeApiRequest(getUrl(apiKey)) {
            addText(prompt)
            addImages(images)
        }
    }

    private suspend fun makeApiRequest(
        url: String,
        requestBuilder: Request.RequestBuilder.() -> Unit
    ): Response {
        val request = Request.RequestBuilder().apply(
            requestBuilder
        ).build()

        val response: String = client.post(url) {
            body = Json.encodeToString(request)
        }.bodyAsText()

        return Json.decodeFromString(response)
    }

    // endregion
    companion object {
        private const val BASE_URL = "https://generativelanguage.googleapis.com"
        private const val ROUTE = "/v1beta/models/gemini-pro:generateContent?key="
    }
}