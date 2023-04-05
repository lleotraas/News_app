package fr.lleotraas.newsapp.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import fr.lleotraas.newsapp.R
import fr.lleotraas.newsapp.presentation.news.NewsViewModel
import fr.lleotraas.newsapp.presentation.utils.Utils
import fr.lleotraas.newsapp.ui.theme.Typography

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(
    navController: NavController,
    articleTitle: String,
    viewModel: NewsViewModel
) {
    val currentArticle = Utils.getCurrentArticle(viewModel.state.value.articles, articleTitle)
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = currentArticle?.title ?: navController.context.resources.getString(R.string.no_title),
            style = Typography.h6,
            textAlign = TextAlign.Center
        )
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            GlideImage(
                model = currentArticle?.urlToImage,
                contentDescription = navController.context.resources.getString(R.string.article_image_description),
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            text = currentArticle?.description ?: navController.context.resources.getString(R.string.no_description),
            style = MaterialTheme.typography.body1
        )
        if (currentArticle?.url != null) {
            val annotatedLinkString: AnnotatedString = buildAnnotatedString {
                val sentence = navController.context.resources.getString(R.string.source)
                val startIndex = sentence.indexOf("link")
                val endIndex = startIndex + 4
                append(sentence)
                addStyle(
                    style = SpanStyle(
                        color = Color.Blue,
                        fontSize = 18.sp,
                        textDecoration = TextDecoration.Underline,
                    ), start = startIndex, end = endIndex
                )

                addStringAnnotation(
                    tag = "URL",
                    annotation = currentArticle.url,
                    start = startIndex,
                    end = endIndex
                )
            }

            val uriHandler = LocalUriHandler.current

            ClickableText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                text = annotatedLinkString,
                onClick = {
                    annotatedLinkString
                        .getStringAnnotations("URL", it, it)
                        .firstOrNull()?.let { stringAnnotation ->
                            uriHandler.openUri(stringAnnotation.item)
                        }
                }
            )
        }
    }
    
}