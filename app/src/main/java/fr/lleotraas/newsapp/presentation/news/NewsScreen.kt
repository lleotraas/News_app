package fr.lleotraas.newsapp.presentation.news

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import fr.lleotraas.newsapp.domain.retrofit.utils.Utils
import fr.lleotraas.newsapp.presentation.news.component.NewsItem
import fr.lleotraas.newsapp.presentation.utils.Screen

@Composable
fun NewsScreen(
    navController: NavController,
    viewModel: NewsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(state.articles) { article ->
            NewsItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(
                            Screen.DetailScreen.route +
                                    "?${Utils.ARTICLE_TITLE}=${article.title}&${Utils.ARTICLE_DESCRIPTION}=${article.description}&${Utils.ARTICLE_IMAGE_URL}=${article.urlToImage}&${Utils.ARTICLE_URL}=${article.url}"
                        )
                    },
                article = article,
                context = navController.context
            )
            Divider(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 48.dp),
                color = MaterialTheme.colors.onSurface,
                thickness = 1.dp
            )
        }
    }

}