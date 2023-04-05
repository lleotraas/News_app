package fr.lleotraas.newsapp.data.repository

import android.util.Log
import fr.lleotraas.newsapp.BuildConfig
import fr.lleotraas.newsapp.domain.model.News
import fr.lleotraas.newsapp.domain.repository.NewsRepository
import fr.lleotraas.newsapp.domain.retrofit.NewsApi
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi
) : NewsRepository {

    private val TAG = javaClass.simpleName

    override suspend fun getNews(search: String, from: String, to: String, language: String,sortBy: String, apiKey: String): News? {
        val response = try {
            api.getNews(search, from, to, language, sortBy, BuildConfig.API_KEY)
        } catch (e: IOException) {
            Log.e(TAG, "getNews: you may have internet connection, ${e.message}")
            return null
        } catch (e: HttpException) {
            Log.e(TAG, "getNews: Http Exception, unexpected response, ${e.message}")
            return null
        }
        return if (response.isSuccessful && response.body() != null) {
            val news: News = response.body()!!
            news
        } else {
            null
        }

    }
}