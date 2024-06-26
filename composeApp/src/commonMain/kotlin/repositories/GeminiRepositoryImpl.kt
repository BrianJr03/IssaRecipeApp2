package repositories

import models.remote.GeminiService
import models.local.Status
import io.ktor.utils.io.errors.IOException

object API {
    val geminiRepository = GeminiRepositoryImpl()
}

class GeminiRepositoryImpl : GeminiRepository {
    private val geminiService = GeminiService()

    override suspend fun generate(
        apiKey: String,
        prompt: String,
        images: List<ByteArray>
    ): Status {
        return try {
            val response = when {
                images.isEmpty() -> geminiService.generateContent(
                    apiKey = apiKey,
                    prompt = prompt
                )
                else -> geminiService.generateContentWithMedia(
                    apiKey = apiKey,
                    prompt = prompt,
                    images =images
                )
            }

            val status = response.error?.let {
                Status.Error(it.message)
            } ?: response.getText()?.let {
                Status.Success(it)
            } ?: Status.Error("An error occurred, please retry.")

            status

        } catch (e: IOException) {
            Status.Error("Unable to connect to the server. " +
                    "Please check your internet connection and try again.")
        } catch (e: Exception) {
            Status.Error("An error occurred, please retry.")
        }
    }
}