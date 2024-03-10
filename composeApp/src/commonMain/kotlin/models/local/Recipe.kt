package models.local

import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
    val imageUrl: String,
    val title: String,
    val content: String,
    val courseType: String,
    val duration: String,
    val rating: String
) {
    override fun toString(): String {
        return "imgUrl: $imageUrl\n" +
                "title: $title\n" +
                "content: $content\n" +
                "course: $courseType\n" +
                "duration: $duration\n" +
                "rating: $rating"
    }

    @Suppress("unused")
    companion object {
        val EMPTY =
            Recipe(
                imageUrl = "",
                title = "",
                content = "",
                courseType = "",
                duration = "",
                rating = ""
            )
    }
}

private const val TEST_RECIPE_PIC = "https://www.foodandwine.com/thmb/fVmYbaQzXCz1Prx8VxrW9sMcjMU=" +
        "/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/Brac" +
        "iole-FT-RECIPE1122-66acf49cef0e4390bec780945709e7f3.jpg"

private const val TEST_RECIPE_PIC2 = "https://www.modernhoney.com/wp-content/uploads/" +
        "2023/01/Ground-Beef-Tacos-11-crop-scaled.jpg"

private const val TEST_RECIPE_PIC3 = "https://www.cookwithmanali.com/wp-co" +
        "ntent/uploads/2014/04/Bhindi-Masala-500x500.jpg"

private const val TEST_CONTENT = "✨ Grilled Peach and Goat Cheese Salad with Pistachios ✨" +
        "\n\nCalories: 350\nFat: 21g\nCarbs: 32g\nProtein: 10g" +
        "\n\nPreparation time: 15 minutes\nCook time: 10 minutes" +
        "\n\nIngredients:\n- 2 peaches\n- 4 cups mixed salad greens" +
        "\n- 1/2 cup crumbled goat cheese\n- 1/4 cup shelled pistachios, chopped" +
        "\n- 2 tablespoons honey\n- 2 tablespoons balsamic vinegar" +
        "\n- 2 tablespoons extra virgin olive oil\n- Salt and pepper to taste" +
        "\n\nInstructions:\n1. Preheat your grill to medium-high heat." +
        "\n2. Cut the peaches in half, remove the pits, and place them cut side down on the grill." +
        " Cook for 3-4 minutes until grill marks appear.\n" +
        "3. Flip the peaches and cook for an additional 2-3 minutes until softened.\n" +
        "4. Remove the peaches from the grill and let them cool slightly. " +
        "Slice each peach half into wedges.\n5. In a small bowl, whisk together honey, " +
        "balsamic vinegar, olive oil, salt, and pepper to create the dressing." +
        "\n6. Arrange the mixed salad greens on a large serving platter or individual plates." +
        "\n7. Top the greens with grilled peach wedges, crumbled goat cheese, and chopped pistachios." +
        "\n8. Drizzle the dressing over the salad.\n9. Serve immediately and enjoy your delightful " +
        "and refreshing Grilled Peach and Goat Cheese Salad\n\nNote: You can add grilled chicken " +
        "or shrimp for an extra protein boost if desired."

val TEST_RECENT_RECIPES = listOf( // TODO - Replace with actual data
    Recipe(
        TEST_RECIPE_PIC,
        "Food 1",
        TEST_CONTENT,
        "Lunch",
        "40 Min",
        "5.0"
    ),
    Recipe(
        TEST_RECIPE_PIC3,
        "Food 2",
        TEST_CONTENT,
        "Breakfast",
        "20 Min",
        "0.2"
    ),
    Recipe(
        TEST_RECIPE_PIC2,
        "Food 3",
        TEST_CONTENT,
        "Dinner",
        "15 Min",
        "3.0"
    ),
    Recipe(
        TEST_RECIPE_PIC3,
        "Food 4",
        TEST_CONTENT,
        "Breakfast",
        "20 Min",
        "0.2"
    ),
    Recipe(
        TEST_RECIPE_PIC2,
        "Food 5",
        TEST_CONTENT,
        "Dinner",
        "15 Min",
        "3.0"
    ),
    Recipe(
        TEST_RECIPE_PIC,
        "Food 5",
        TEST_CONTENT,
        "Lunch",
        "40 Min",
        "5.0"
    ),
    Recipe(
        TEST_RECIPE_PIC3,
        "Food 6",
        TEST_CONTENT,
        "Breakfast",
        "20 Min",
        "0.2"
    ),
    Recipe(
        TEST_RECIPE_PIC,
        "Food 7",
        TEST_CONTENT,
        "Lunch",
        "40 Min",
        "5.0"
    ),
    Recipe(
        TEST_RECIPE_PIC2,
        "Food 8",
        TEST_CONTENT,
        "Dinner",
        "15 Min",
        "3.0"
    )
)