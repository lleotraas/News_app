package fr.lleotraas.newsapp.data.repository

import fr.lleotraas.newsapp.domain.model.News
import fr.lleotraas.newsapp.domain.repository.NewsRepository
import fr.lleotraas.newsapp.domain.retrofit.NewsApi
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi
) : NewsRepository {

    override suspend fun getNews(search: String, from: String, to: String, language: String,sortBy: String, apiKey: String): Response<News> {
        return api.getNews(search, from, to, language, sortBy, apiKey)
    }
}