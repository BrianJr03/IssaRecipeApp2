package ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import blocs.settingsScreen.SettingsScreenComponent
import blocs.settingsScreen.SettingsScreenEvent
import kotlinx.coroutines.launch
import models.local.LocalStorage
import models.local.SqlDataSourceImpl
import ui.animation.DefaultLoadingAnimation
import ui.composables.DefaultTextField
import ui.composables.OptionsDialog
import util.API_KEY_LABEL
import util.DEFAULT_API_KEY_VALUE
import util.DIETARY_RESTRICTIONS_LABEL
import util.FOOD_ALLERGY_LABEL
import util.allergyOptions
import util.dietaryOptions

@Composable
fun SettingsScreenComponent.SettingsScreen(
    sqlDataSourceImpl: SqlDataSourceImpl
) {
    val apiKey = rememberSaveable { mutableStateOf(DEFAULT_API_KEY_VALUE) }
    val dietarySettings = rememberSaveable { mutableStateOf("") }
    val allergySettings = rememberSaveable { mutableStateOf("") }

    LaunchedEffect(1) {
        try {
            sqlDataSourceImpl.settings.collect {
                    apiKey.value = it.apiKey
                    dietarySettings.value = it.dietarySettings
                    allergySettings.value = it.allergySettings
                }
        } catch (npe: NullPointerException) {
            apiKey.value = ""
            sqlDataSourceImpl.initSettings()
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if (apiKey.value != DEFAULT_API_KEY_VALUE) {
            SettingsPage(
                sqlDataSourceImpl = sqlDataSourceImpl,
                apiKey = apiKey.value,
                dietaryRestrictions = dietarySettings.value,
                foodAllergies = allergySettings.value,
                autoGenerateImage = false,
            ) {
                onEvent(SettingsScreenEvent.OnNavBack)
            }
        } else {
            DefaultLoadingAnimation()
        }
    }
}

@Composable
fun SettingsPage(
    sqlDataSourceImpl: SqlDataSourceImpl,
    apiKey: String,
    dietaryRestrictions: String,
    foodAllergies: String,
    autoGenerateImage: Boolean,
    onNavBack: () -> Unit
) {
    val scope = rememberCoroutineScope()

    val key = remember {
        mutableStateOf(apiKey)
    }

    val dietary = remember {
        mutableStateOf(dietaryRestrictions)
    }

    val allergies = remember {
        mutableStateOf(foodAllergies)
    }

    val isImageGenEnabled = remember {
        mutableStateOf(autoGenerateImage)
    }

    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }

    Scaffold {
        Settings(
            sqlDataSourceImpl = sqlDataSourceImpl,
            apiKey = key.value,
            dietaryRestrictions = dietaryRestrictions,
            isImageGenEnabled = isImageGenEnabled.value,
            foodAllergies = foodAllergies,
            onApiKeyValueChange = { str ->
                key.value = str
                scope.launch {
                    sqlDataSourceImpl.updateApiKey(str)
                }
            },
            onDietaryValueChange = { str ->
                dietary.value = str
                scope.launch {
                    sqlDataSourceImpl.updateDietarySetting(str.lowercase())
                }
            },
            onAllergiesValueChange = { str ->
                allergies.value = str
                scope.launch {
                    sqlDataSourceImpl.updateAllergySetting(str.lowercase())
                }
            },
            onEnableImageGenCheckChange = { isChecked ->
                LocalStorage.saveAutoGenerateImage(isChecked)
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .clickable(interactionSource = interactionSource, indication = null) {
                    focusManager.clearFocus()
                },
            onClearSettings = {
                key.value = ""
                dietary.value = ""
                allergies.value = ""
                isImageGenEnabled.value = false
                LocalStorage.keyValueStorage.clearSettings()
            },
            onNavBack = { onNavBack() }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Settings(
    sqlDataSourceImpl: SqlDataSourceImpl,
    apiKey: String,
    dietaryRestrictions: String,
    isImageGenEnabled: Boolean,
    foodAllergies: String,
    onApiKeyValueChange: (String) -> Unit,
    onDietaryValueChange: (String) -> Unit,
    onAllergiesValueChange: (String) -> Unit,
    onEnableImageGenCheckChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    onClearSettings: () -> Unit,
    onNavBack: () -> Unit,
) {
    val focusManager = LocalFocusManager.current

    val scope = rememberCoroutineScope()

    val isDietaryOptionsShowing = remember {
        mutableStateOf(false)
    }

    val isAllergyOptionsShowing = remember {
        mutableStateOf(false)
    }

    val isModelOptionsShowing = remember {
        mutableStateOf(false)
    }

    val isClearFavsInfoShowing = remember {
        mutableStateOf(true)
    }

    val isClearSettingsInfoShowing = remember {
        mutableStateOf(true)
    }

    val clearFavoritesLabel = remember {
        mutableStateOf("Clear All Favorites")
    }

    val clearSettingsLabel = remember {
        mutableStateOf("Clear All Settings")
    }

    val switchedCheckedState = remember {
        mutableStateOf(isImageGenEnabled)
    }

    val handleLabelChange = {
        when (!isClearFavsInfoShowing.value) {
            true -> clearFavoritesLabel.value = "Long-Press To Confirm"
            false -> clearFavoritesLabel.value = "Clear All Favorites"
        }
        when (!isClearSettingsInfoShowing.value) {
            true -> clearSettingsLabel.value = "Long-Press To Confirm"
            false -> clearSettingsLabel.value = "Clear Settings"
        }
    }

    OptionsDialog(
        isShowing = isDietaryOptionsShowing.value,
        title = "Restrictions",
        options = dietaryOptions,
        onSelectItem = {
            onDietaryValueChange(it)
            isDietaryOptionsShowing.value = false
        },
        onDismissRequest = {
            isDietaryOptionsShowing.value = false
        }
    )

    OptionsDialog(
        isShowing = isAllergyOptionsShowing.value,
        title = "Allergies",
        options = allergyOptions,
        onSelectItem = {
            onAllergiesValueChange(it)
            isAllergyOptionsShowing.value = false
        },
        onDismissRequest = {
            isAllergyOptionsShowing.value = false
        }
    )

    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.height(15.dp))

            DefaultTextField(
                label = API_KEY_LABEL,
                value = apiKey,
                onValueChange = onApiKeyValueChange,
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    Icon(
                        Icons.Rounded.Info,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = "API Key",
                        modifier = Modifier.clickable {

                        }
                    )
                }
            )

            DefaultTextField(
                label = DIETARY_RESTRICTIONS_LABEL,
                value = dietaryRestrictions,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = onDietaryValueChange,
                trailingIcon = {
                    Icon(
                        Icons.Rounded.Menu,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = "View preset dietary restrictions",
                        modifier = Modifier.clickable {
                            focusManager.clearFocus()
                            isAllergyOptionsShowing.value = false
                            isModelOptionsShowing.value = false
                            isDietaryOptionsShowing.value = !isDietaryOptionsShowing.value
                        }
                    )
                }
            )

            DefaultTextField(
                label = FOOD_ALLERGY_LABEL,
                value = foodAllergies,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = onAllergiesValueChange,
                trailingIcon = {
                    Icon(
                        Icons.Rounded.Menu,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = "View preset food allergies",
                        modifier = Modifier.clickable {
                            focusManager.clearFocus()
                            isDietaryOptionsShowing.value = false
                            isModelOptionsShowing.value = false
                            isAllergyOptionsShowing.value = !isAllergyOptionsShowing.value
                        }
                    )
                }
            )

            SettingsDivider()

            Row(verticalAlignment = Alignment.CenterVertically) {
                Switch(
                    checked = switchedCheckedState.value,
                    onCheckedChange = { isChecked ->
                        switchedCheckedState.value = isChecked
                        onEnableImageGenCheckChange(isChecked)
                    },
                    modifier = Modifier.padding(end = 16.dp)
                )
                Column {
                    Text(
                        text = "Auto Image Generation", style = TextStyle(
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 20.sp
                        )
                    )
                    Text(
                        text = "Used when generating recipes in Generate." +
                                "\nThis is costly compared to text generation." +
                                "\nTap here to monitor your API usage.",
                        style = TextStyle(fontSize = 12.sp),
                        modifier = Modifier.clickable {
//                            val intent = Intent(Intent.ACTION_VIEW)
//                            intent.data = Uri.parse(API_USAGE_URL)
//                            context.startActivity(intent)
                        }
                    )
                }
            }

            SettingsDivider()

            Column {
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = clearFavoritesLabel.value,
                    color = Color.Red,
                    style = TextStyle(fontSize = 20.sp),
                    modifier = Modifier.combinedClickable(
                        onLongClick = {
                            if (!isClearFavsInfoShowing.value) {
                                scope.launch {
                                    sqlDataSourceImpl.removeAllRecipes()
                                }
//                                Toast.makeText(context, "Cleared!", Toast.LENGTH_SHORT).show()
                                handleLabelChange()
                            }
                        },
                        onClick = {
                            scope.launch {
                                isClearFavsInfoShowing.value = !isClearFavsInfoShowing.value
                                handleLabelChange()
                            }
                        })
                )
            }

            Spacer(Modifier.height(15.dp))

            Text(
                text = clearSettingsLabel.value,
                color = Color.Red,
                style = TextStyle(fontSize = 20.sp),
                modifier = Modifier.combinedClickable(
                    onLongClick = {
                        if (!isClearSettingsInfoShowing.value) {
                            onClearSettings()
                            isClearSettingsInfoShowing.value = !isClearSettingsInfoShowing.value
                            handleLabelChange()
                        }
                    },
                    onClick = {
                        scope.launch {
                            isClearSettingsInfoShowing.value = !isClearSettingsInfoShowing.value
                            handleLabelChange()
                        }
                    })
            )

            Spacer(Modifier.height(15.dp))

            Button(onClick = { onNavBack() }) {
                Text("Back")
            }
        }
    }
}

@Composable
fun SettingsDivider() {
    Column {
        Spacer(modifier = Modifier.height(15.dp))
        Divider(
            color = MaterialTheme.colorScheme.primary,
            thickness = 5.dp,
            modifier = Modifier.width(300.dp)
        )
        Spacer(modifier = Modifier.height(15.dp))
    }
}