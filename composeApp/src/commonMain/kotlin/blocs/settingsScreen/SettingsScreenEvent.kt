package blocs.settingsScreen

sealed interface SettingsScreenEvent {
    data object OnNavBack: SettingsScreenEvent
    data object OnSaveApiKey: SettingsScreenEvent
}