package ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import blocs.seeAllScreen.SeeAllScreenComponent
import blocs.seeAllScreen.SeeAllScreenEvent
import util.DEFAULT_TEXT_STYLE
import constants.YOU_GOTTA_TRY_THIS
import util.cards.HORIZONTAL_CARD_HEIGHT
import util.cards.OPTION_CARD_PADDING_END
import util.getCardInListColor
import util.home.DEFAULT_PADDING_END
import ui.composables.HorizontalRecipeCard
import util.home.DEFAULT_PADDING_START
import util.home.ROW_LABEL_PADDING_BOTTOM
import util.defaultVerticalGradient
import models.local.TEST_RECENT_RECIPES
import ui.composables.CourseOptionCardRow
import ui.composables.DefaultTopAppBar

@Composable
fun SeeAllScreenComponent.SeeAllScreen() {
    Scaffold(
        topBar = {
            DefaultTopAppBar(
                onBackClick = {
                    onEvent(
                        SeeAllScreenEvent.OnNavBack
                    )
                }
            )
        }
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            item(
                span = StaggeredGridItemSpan.FullLine
            ) {
                Column {
                    CourseOptionCardRow(
                        optionCard1Modifier = Modifier
                            .height(50.dp)
                            .background(defaultVerticalGradient())
                            .padding(
                                start = DEFAULT_PADDING_START,
                                end = DEFAULT_PADDING_END
                            ),
                        optionCard2Modifier = Modifier
                            .height(50.dp)
                            .background(defaultVerticalGradient())
                            .padding(
                                start = DEFAULT_PADDING_START,
                                end = DEFAULT_PADDING_END
                            ),
                        optionCard3Modifier = Modifier
                            .height(50.dp)
                            .background(defaultVerticalGradient())
                            .padding(
                                start = DEFAULT_PADDING_START,
                                end = DEFAULT_PADDING_END
                            ),
                        optionCard4Modifier = Modifier
                            .height(50.dp)
                            .background(defaultVerticalGradient())
                            .padding(
                                start = DEFAULT_PADDING_START,
                                end = DEFAULT_PADDING_END
                            )
                    )
                    Spacer(Modifier.height(20.dp))
                }
            }

            item(
                span = StaggeredGridItemSpan.FullLine
            ) {
                Text(
                    YOU_GOTTA_TRY_THIS,
                    style = DEFAULT_TEXT_STYLE,
                    modifier = Modifier.padding(
                        start = DEFAULT_PADDING_START,
                        bottom = ROW_LABEL_PADDING_BOTTOM
                    )
                )
            }

            items(TEST_RECENT_RECIPES.size) {
                HorizontalRecipeCard(
                    TEST_RECENT_RECIPES[it],
                    modifier = Modifier
                        .height(HORIZONTAL_CARD_HEIGHT)
                        .padding(
                            start = DEFAULT_PADDING_START,
                            bottom = 10.dp,
                            end = OPTION_CARD_PADDING_END
                        ),
                    color = getCardInListColor(it)
                ) {
                    onEvent(
                        SeeAllScreenEvent.OnRecentRecipeClick(TEST_RECENT_RECIPES[it])
                    )
                }
            }
        }
    }
}