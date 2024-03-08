package ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import blocs.askScreen.GenerateScreenComponent
import blocs.generateScreen.GenerateScreenEvent
import constants.DIETARY_LABEL
import constants.FOOD_ALLERGIES
import constants.INGREDIENTS_LABEL
import constants.OCCASION_LABEL
import constants.OTHER_INFO
import constants.PARTY_SIZE_LABEL
import ui.composables.DefaultTextField

@Composable
fun GenerateScreenComponent.GenerateScreen() {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                focusManager.clearFocus()
            }
    ) {
        val ingredients = remember { mutableStateOf("") }
        val partySize = remember { mutableStateOf("") }
        val occasion = remember { mutableStateOf("") }
        val dietaryRestrictions = remember { mutableStateOf("") }
        val foodAllergies = remember { mutableStateOf("") }
        val otherInfo = remember { mutableStateOf("") }

        val isIngredientsFocused = remember { mutableStateOf(false) }
        val isPartySizeFocused = remember { mutableStateOf(false) }
        val isOccasionFocused = remember { mutableStateOf(false) }
        val isDietaryFocused = remember { mutableStateOf(false) }
        val isAllergiesFocused = remember { mutableStateOf(false) }
        val isOtherFocused = remember { mutableStateOf(false) }

        val isIngredientsOnlyFocused = !isPartySizeFocused.value &&
                !isOccasionFocused.value &&
                !isDietaryFocused.value &&
                !isAllergiesFocused.value &&
                !isOtherFocused.value

        val isPartySizeOnlyFocused = !isIngredientsFocused.value &&
                !isOccasionFocused.value &&
                !isDietaryFocused.value &&
                !isAllergiesFocused.value &&
                !isOtherFocused.value

        val isOccasionOnlyFocused = !isIngredientsFocused.value &&
                !isPartySizeFocused.value &&
                !isDietaryFocused.value &&
                !isAllergiesFocused.value &&
                !isOtherFocused.value

        val isDietaryOnlyFocused = !isIngredientsFocused.value &&
                !isOccasionFocused.value &&
                !isPartySizeFocused.value &&
                !isAllergiesFocused.value &&
                !isOtherFocused.value

        val isAllergiesOnlyFocused = !isIngredientsFocused.value &&
                !isOccasionFocused.value &&
                !isDietaryFocused.value &&
                !isPartySizeFocused.value &&
                !isOtherFocused.value

        val isOtherOnlyFocused = !isIngredientsFocused.value &&
                !isOccasionFocused.value &&
                !isDietaryFocused.value &&
                !isAllergiesFocused.value &&
                !isPartySizeFocused.value

        AnimatedVisibility(visible = isIngredientsOnlyFocused) {
            DefaultTextField(
                label = INGREDIENTS_LABEL,
                value = ingredients.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        isIngredientsFocused.value = it.isFocused
                    },
                onValueChange = {
                    ingredients.value = it
                },
                onDone = {
                    focusManager.clearFocus()
                }
            )
        }

        AnimatedVisibility(visible = isPartySizeOnlyFocused) {
            DefaultTextField(
                label = PARTY_SIZE_LABEL,
                value = partySize.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        isPartySizeFocused.value = it.isFocused
                    },
                onValueChange = {
                    partySize.value = it
                },
                onDone = {
                    focusManager.clearFocus()
                }
            )
        }

        AnimatedVisibility(visible = isOccasionOnlyFocused) {
            DefaultTextField(
                label = OCCASION_LABEL,
                value = occasion.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        isOccasionFocused.value = it.isFocused
                    },
                onValueChange = {
                    occasion.value = it
                },
                onDone = {
                    focusManager.clearFocus()
                }
            )
        }

        AnimatedVisibility(visible = isDietaryOnlyFocused) {
            DefaultTextField(
                label = DIETARY_LABEL,
                value = foodAllergies.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        isDietaryFocused.value = it.isFocused
                    },
                onValueChange = {
                    foodAllergies.value = it
                },
                onDone = {
                    focusManager.clearFocus()
                }
            )
        }

        AnimatedVisibility(visible = isAllergiesOnlyFocused) {
            DefaultTextField(
                label = FOOD_ALLERGIES,
                value = dietaryRestrictions.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        isAllergiesFocused.value = it.isFocused
                    },
                onValueChange = {
                    dietaryRestrictions.value = it
                },
                onDone = {
                    focusManager.clearFocus()
                }
            )
        }

        AnimatedVisibility(visible = isOtherOnlyFocused) {
            DefaultTextField(
                label = OTHER_INFO,
                value = otherInfo.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        isOtherFocused.value = it.isFocused
                    },
                onValueChange = {
                    otherInfo.value = it
                },
                onDone = {
                    focusManager.clearFocus()
                }
            )
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 15.dp,
                    end = 15.dp
                ),
            onClick = {
                onEvent(GenerateScreenEvent.OnGenerateRecipe)
            }) {
            Text("Generate")
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 15.dp,
                    end = 15.dp
                ),
            onClick = {
                onEvent(GenerateScreenEvent.OnRandomizeRecipe)
            }) {
            Text("Randomize")
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 15.dp,
                    end = 15.dp
                ),
            onClick = {
                onEvent(GenerateScreenEvent.OnNavBack)
            }) {
            Text("Back")
        }
    }
}