package fr.lleotraas.newsapp.presentation.utils

sealed class Screen(
    val route: String
    ) {
    object NewsScreen: Screen("news_screen")
    object DetailScreen: Screen("detail_screen")
}