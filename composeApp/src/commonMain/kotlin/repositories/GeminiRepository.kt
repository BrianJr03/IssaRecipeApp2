package repositories

import models.local.Status

interface GeminiRepository {
    suspend fun generate(
        apiKey: String,
        prompt: String,
        images: List<ByteArray> = emptyList()
    ): Status
}
