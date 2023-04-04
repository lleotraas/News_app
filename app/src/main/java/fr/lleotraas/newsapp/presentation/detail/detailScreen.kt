package fr.lleotraas.newsapp.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import fr.lleotraas.newsapp.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(
    navController: NavController,
    title: String?,
    description: String?,
    imageUrl: String?,
    articleUrl: String?
) {

    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = title ?: navController.context.resources.getString(R.string.no_title))
        GlideImage(
            model = imageUrl,
            contentDescription = navController.context.resources.getString(R.string.article_image_description),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Text(
            text = description ?: navController.context.resources.getString(R.string.no_description)
        )
        if (articleUrl != null) {
            Text(text = articleUrl)
        }
    }
    
}