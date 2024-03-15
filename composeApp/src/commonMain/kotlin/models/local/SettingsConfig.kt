package models.local

import kotlinx.serialization.Serializable

@Serializable
data class SettingsConfig(
    val dietaryRestrictions: String,
    val foodAllergies: String,
    val apiKey: String,
    val autoGenerateImage: Boolean,
) {
    companion object {
        val EMPTY = SettingsConfig(
            dietaryRestrictions = "",
            foodAllergies = "",
            apiKey = "",
            autoGenerateImage = false
        )
    }
}