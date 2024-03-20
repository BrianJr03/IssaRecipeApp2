package util

const val API_KEY_LABEL = "API Key"
const val DIETARY_RESTRICTIONS_LABEL = "Dietary Restrictions"
const val FOOD_ALLERGY_LABEL = "Food Allergies"
const val DEFAULT_RECIPE_TITLE = "Recipe"

val loadingHints = listOf(
    "Do you smell what's cooking?",
    "Generating thy Recipe",
    "Are you excited? We are!",
    "This Recipe is gonna hit different",
    "Preparing something special"
)

val occasionOptions =
    listOf(
        "breakfast",
        "brunch",
        "lunch",
        "snack",
        "dinner",
        "dessert",
        "any occasion"
    )

val dietaryOptions = listOf(
    "lactose intolerance",
    "gluten intolerance",
    "vegetarian",
    "vegan",
    "kosher",
    "none"
)

val allergyOptions = listOf(
    "dairy",
    "peanuts",
    "fish",
    "soy",
    "sesame",
    "none"
)