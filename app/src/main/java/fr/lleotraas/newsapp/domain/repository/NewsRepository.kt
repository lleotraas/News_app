package fr.lleotraas.newsapp.domain.repository

import fr.lleotraas.newsapp.domain.model.News
import retrofit2.Response

interface NewsRepository {

   suspend fun getNews(search: String, from: String, to: String, language: String,sortBy: String, apiKey: String): Response<News>

}