package models.local

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.getStringFlow
import com.russhwolf.settings.serialization.decodeValueOrNull
import com.russhwolf.settings.serialization.encodeValue
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.ExperimentalSerializationApi

object LocalStorage {
    val keyValueStorage: KeyValueStorage = KeyValueStorageImpl()

    fun saveApiKey(value: String) {
        keyValueStorage.settingsConfig = keyValueStorage.settingsConfig?.copy(
            apiKey = value
        )
    }

    fun saveFoodAllergies(value: String) {
        keyValueStorage.settingsConfig = keyValueStorage.settingsConfig?.copy(
            foodAllergies = value
        )
    }

    fun saveDietaryRestrictions(value: String) {
        keyValueStorage.settingsConfig = keyValueStorage.settingsConfig?.copy(
            dietaryRestrictions = value
        )
    }

    fun saveAutoGenerateImage(value: Boolean) {
        keyValueStorage.settingsConfig = keyValueStorage.settingsConfig?.copy(
//            autoGenerateImage = value
        )
    }
}

interface KeyValueStorage {
    var settingsConfig: SettingsConfig?
    val settingsConfigFlow: Flow<String>

    fun clearFavorites()
    fun clearSettings()
}

enum class StorageKeys {
    SETTINGS_CONFIG;

    val key get() = this.name
}

class KeyValueStorageImpl : KeyValueStorage {
    private val settings: Settings by lazy { Settings() }
    private val observableSettings: ObservableSettings by lazy { settings as ObservableSettings }

    @OptIn(ExperimentalSerializationApi::class, ExperimentalSettingsApi::class)
    override var settingsConfig: SettingsConfig?
        get() = settings.decodeValueOrNull(
            SettingsConfig.serializer(),
            StorageKeys.SETTINGS_CONFIG.key
        )
        set(value) {
            if (value != null) {
                settings.encodeValue(
                    SettingsConfig.serializer(),
                    StorageKeys.SETTINGS_CONFIG.key, value
                )
            }
        }

    @OptIn(ExperimentalSettingsApi::class)
    override val settingsConfigFlow: Flow<String>
        get() = observableSettings.getStringFlow(StorageKeys.SETTINGS_CONFIG.key, "")

    override fun clearSettings() {
        settings.clear()
    }

    override fun clearFavorites() {

    }
}