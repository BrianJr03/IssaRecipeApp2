package util

import androidx.compose.ui.graphics.Color

val COLOR_GOLD = Color.hsv(46F, 0.73F, 0.96F)
val COLOR_GREEN = Color.hsv(129F, 0.86F, 0.68F)

val COLOR_WHEAT = Color(255, 232, 186)
val COLOR_STEEL_BLUE = Color(99, 184, 255)

val COLOR_PALE_VIOLET_RED = Color(255, 130, 171)
val COLOR_ELECTRIC_TURQUOISE = Color(74, 232, 189)
val COLOR_FIRE_BRICK_RED = Color(255, 48, 48)

fun getCardInListColor(index: Int) = if (index % 2 == 0) COLOR_WHEAT else COLOR_STEEL_BLUE