package fr.lleotraas.newsapp.presentation.news.component

import android.content.Context
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import fr.lleotraas.newsapp.R
import fr.lleotraas.newsapp.domain.model.Article
import fr.lleotraas.newsapp.ui.theme.Typography

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsItem(
    article: Article,
    modifier: Modifier,
    context: Context
){
    Box(modifier = modifier) {
        Column(modifier = Modifier.fillMaxSize()){
            GlideImage(
                model = article.urlToImage,
                contentDescription = context.resources.getString(R.string.article_image_description),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = article.title,
                style = Typography.h6
            )
        }
    }
}