package util

fun generateRecipeQuery(
    ingredients: String,
    partySize: String,
    occasion: String,
    dietaryRestrictions: String,
    foodAllergies: String,
    otherInfo: String,
) = "Generate a recipe for $occasion that serves $partySize " +
        "using the following ingredients: $ingredients. " +
        "Keep in mind the following " +
        "dietary restrictions: $dietaryRestrictions. " +
        "Also note that I am allergic to $foodAllergies. " +
        "Please include the estimated calories, fat, carbs, protein " +
        "and preparation / cook time. " +
        "Also, title the recipe and surround it in '✨' for easy extraction. " +
        if (otherInfo.isNotBlank())
            "Lastly, here is some additional info for this recipe:" +
                    " $otherInfo. Thanks!"
        else ""

fun generateAskQuery(
    system: String? = null
) = "You are a 5 star chef. " +
        if (system.isNullOrBlank()) "" else "$system " +
                "\nLastly, only respond to questions that are about " +
                "preparing food, " +
                "cooking food, " +
                "providing recipes, " +
                "providing culinary advice, " +
                "or anything that generally has to do with any aspect of your job." +
                "Politely decline anything outside of that list."