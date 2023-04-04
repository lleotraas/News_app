package fr.lleotraas.newsapp.domain.retrofit

import fr.lleotraas.newsapp.domain.model.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/everything/")
   suspend fun getNews(
        @Query("q") search: String,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("language") language: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") key: String
    ): Response<News>

}