package fr.lleotraas.newsapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import fr.lleotraas.newsapp.domain.retrofit.utils.Utils
import fr.lleotraas.newsapp.presentation.detail.DetailScreen
import fr.lleotraas.newsapp.presentation.utils.Screen
import fr.lleotraas.newsapp.presentation.news.NewsScreen
import fr.lleotraas.newsapp.ui.theme.NewsAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.NewsScreen.route) {
                        composable(route = Screen.NewsScreen.route) {
                            NewsScreen(navController = navController)
                        }
                        composable(
                            route = Screen.DetailScreen.route +
                                    "?${Utils.ARTICLE_TITLE}={${Utils.ARTICLE_TITLE}}&${Utils.ARTICLE_DESCRIPTION}={${Utils.ARTICLE_DESCRIPTION}}&${Utils.ARTICLE_IMAGE_URL}={${Utils.ARTICLE_IMAGE_URL}}&${Utils.ARTICLE_URL}={${Utils.ARTICLE_URL}}",
                            arguments = listOf(
                                navArgument(
                                    name = Utils.ARTICLE_TITLE
                                ) {
                                    type = NavType.StringType
                                    defaultValue = ""
                                },
                                navArgument(
                                    name = Utils.ARTICLE_DESCRIPTION
                                ) {
                                    type = NavType.StringType
                                    defaultValue = ""
                                },
                                navArgument(
                                    name = Utils.ARTICLE_IMAGE_URL
                                ) {
                                    type = NavType.StringType
                                    defaultValue = ""
                                },
                                navArgument(
                                    name = Utils.ARTICLE_URL
                                ) {
                                    type = NavType.StringType
                                    defaultValue = ""
                                }
                            )
                        ) {
                            val title = it.arguments?.getString(Utils.ARTICLE_TITLE)
                            val description = it.arguments?.getString(Utils.ARTICLE_DESCRIPTION)
                            val imageUrl = it.arguments?.getString(Utils.ARTICLE_IMAGE_URL)
                            val articleUrl = it.arguments?.getString(Utils.ARTICLE_URL)
                            DetailScreen(
                                navController = navController,
                                title = title,
                                description = description,
                                imageUrl = imageUrl,
                                articleUrl = articleUrl
                            )
                        }
                    }
                }
            }
        }
    }
}