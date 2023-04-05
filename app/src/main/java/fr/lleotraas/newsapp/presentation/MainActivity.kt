package fr.lleotraas.newsapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import fr.lleotraas.newsapp.domain.retrofit.utils.Utils
import fr.lleotraas.newsapp.presentation.detail.DetailScreen
import fr.lleotraas.newsapp.presentation.news.NewsScreen
import fr.lleotraas.newsapp.presentation.news.NewsViewModel
import fr.lleotraas.newsapp.presentation.utils.Screen
import fr.lleotraas.newsapp.ui.theme.NewsAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    val viewModel: NewsViewModel = hiltViewModel()
                    NavHost(navController = navController, startDestination = Screen.NewsScreen.route) {
                        composable(route = Screen.NewsScreen.route) {
                            NewsScreen(
                                navController = navController,
                                viewModel = viewModel
                            )
                        }
                        composable(
                                    route = Screen.DetailScreen.route
                                    +
                                    "?${Utils.ARTICLE_TITLE}={${Utils.ARTICLE_TITLE}}",
                                    arguments = listOf(
                                        navArgument(
                                            name = Utils.ARTICLE_TITLE
                                ) {
                                    type = NavType.StringType
                                    defaultValue = "none"
                                }
                            )
                        )
                        {
                            val title = it.arguments?.getString(Utils.ARTICLE_TITLE)
                            DetailScreen(
                                navController = navController,
                                articleTitle = title ?: "",
                                viewModel = viewModel
                                )
                        }
                    }
                }
            }
        }
    }
}