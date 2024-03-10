package ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import blocs.generateScreen.GenerateScreenComponent
import blocs.generateScreen.GenerateScreenEvent
import constants.DIETARY_LABEL
import constants.FOOD_ALLERGIES
import constants.INGREDIENTS_LABEL
import constants.OCCASION_LABEL
import constants.OTHER_INFO
import constants.PARTY_SIZE_LABEL
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import models.local.Recipe
import models.local.TEST_RECENT_RECIPES
import ui.composables.DefaultTextField
import util.validateAllergies
import util.validateDietary
import util.validateIngredients
import util.validateOccasion
import util.validateOtherInfo
import util.validatePartySize

@Composable
fun GenerateScreenComponent.GenerateScreen() {
    val scope = rememberCoroutineScope()
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
        val ingredients = rememberSaveable { mutableStateOf("") }
        val partySize = rememberSaveable { mutableStateOf("") }
        val occasion = rememberSaveable { mutableStateOf("") }
        val dietaryRestrictions = rememberSaveable { mutableStateOf("") }
        val foodAllergies = rememberSaveable { mutableStateOf("") }
        val otherInfo = rememberSaveable { mutableStateOf("") }

        val isIngredientsFocused = remember { mutableStateOf(false) }
        val isPartySizeFocused = remember { mutableStateOf(false) }
        val isOccasionFocused = remember { mutableStateOf(false) }
        val isDietaryFocused = remember { mutableStateOf(false) }
        val isAllergiesFocused = remember { mutableStateOf(false) }
        val isOtherFocused = remember { mutableStateOf(false) }
        val isEmptyIngredientsError = remember { mutableStateOf(false) }

        val isLoading = rememberSaveable { mutableStateOf(false) }
        val isRecipeGenerated = rememberSaveable { mutableStateOf(false) }

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

        val validateTextFields = {
            focusManager.clearFocus()
            ingredients.value = ingredients.value.validateIngredients()
            partySize.value = partySize.value.validatePartySize()
            occasion.value = occasion.value.validateOccasion()
            dietaryRestrictions.value = dietaryRestrictions.value.validateDietary()
            foodAllergies.value = foodAllergies.value.validateAllergies()
            otherInfo.value = otherInfo.value.validateOtherInfo()
        }

        val resetTextFields = {
            focusManager.clearFocus()
            ingredients.value = ""
            partySize.value = ""
            occasion.value = ""
            dietaryRestrictions.value = ""
            foodAllergies.value = ""
            otherInfo.value = ""
            isEmptyIngredientsError.value = false
        }

        Spacer(Modifier.height(15.dp))

        AnimatedVisibility(visible = isIngredientsOnlyFocused) {
            DefaultTextField(
                label = INGREDIENTS_LABEL,
                value = ingredients.value,
                isError = isEmptyIngredientsError.value,
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

        AnimatedVisibility(isRecipeGenerated.value && !isLoading.value) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 15.dp,
                        end = 15.dp
                    ),
                onClick = {
                    onEvent(
                        GenerateScreenEvent.OnGenerateRecipe(
                            recipe = RecipeCache.recipe
                        )
                    )
                }) {
                Text("View Again")
            }
        }

        AnimatedVisibility(!isLoading.value) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 15.dp,
                        end = 15.dp
                    ),
                onClick = {
                    if (ingredients.value.isNotEmpty()) {
                        scope.launch {
                            isRecipeGenerated.value = false
                            isLoading.value = true
                            validateTextFields()
                            delay(3000) // TODO - FETCH DATA HERE
                            isRecipeGenerated.value = true
                            isLoading.value = false
                            RecipeCache.recipe = TEST_RECENT_RECIPES.random() // TODO - POPULATE WITH ACTUAL DATA
                            onEvent(
                                GenerateScreenEvent.OnGenerateRecipe(
                                    recipe = RecipeCache.recipe
                                )
                            )
                        }
                    } else {
                        isEmptyIngredientsError.value = true
                    }
                }) {
                Text("Generate")
            }
        }

        AnimatedVisibility(!isLoading.value) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 15.dp,
                        end = 15.dp
                    ),
                onClick = {
                    scope.launch {
                        isRecipeGenerated.value = false
                        isLoading.value = true
                        resetTextFields()
                        validateTextFields()
                        delay(3000) // TODO - FETCH DATA HERE
                        isRecipeGenerated.value = true
                        isLoading.value = false
                        RecipeCache.recipe = TEST_RECENT_RECIPES.random() // TODO - POPULATE WITH ACTUAL DATA
                        onEvent(
                            GenerateScreenEvent.OnRandomizeRecipe(
                                recipe = RecipeCache.recipe
                            )
                        )
                    }
                }) {
                Text("Randomize")
            }
        }

        AnimatedVisibility(isLoading.value) {
            Text(
                text = "Generating Your Recipe ...",
                style = TextStyle(fontSize = 16.sp),
                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
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
                onEvent(GenerateScreenEvent.OnNavBack)
            }) {
            Text("Back")
        }
    }
}

private object RecipeCache {
    var recipe = Recipe.EMPTY

    @Suppress("unused")
    fun reset() {
        recipe = Recipe.EMPTY
    }
}