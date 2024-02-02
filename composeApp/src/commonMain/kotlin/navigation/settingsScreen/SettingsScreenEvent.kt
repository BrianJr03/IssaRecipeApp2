package navigation.settingsScreen

sealed interface SettingsScreenEvent {
    data object OnNavBack: SettingsScreenEvent
    data object OnSaveApiKey: SettingsScreenEvent
}