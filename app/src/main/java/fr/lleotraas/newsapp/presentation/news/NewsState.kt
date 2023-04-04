package fr.lleotraas.newsapp.presentation.news

import fr.lleotraas.newsapp.domain.model.Article

data class NewsState(
    val articles: List<Article> = emptyList()
)
