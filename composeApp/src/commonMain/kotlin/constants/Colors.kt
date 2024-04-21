package constants

import androidx.compose.ui.graphics.Color

val GOLD = Color.hsv(46F, 0.73F, 0.96F)
val GREEN = Color.hsv(129F, 0.86F, 0.68F)

val WHEAT = Color(255, 232, 186)
val STEEL_BLUE = Color(99, 184, 255)
val PALE_VIOLET_RED = Color(255, 130, 171)
val ELECTRIC_TURQUOISE = Color(74, 232, 189)
val FIRE_BRICK_RED = Color(255, 48, 48)

fun getCardInListColor(index: Int) = if (index % 2 == 0) WHEAT else STEEL_BLUE