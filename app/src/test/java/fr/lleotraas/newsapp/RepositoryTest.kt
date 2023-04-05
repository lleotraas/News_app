package fr.lleotraas.newsapp

import fr.lleotraas.newsapp.data.repository.NewsRepositoryImpl
import fr.lleotraas.newsapp.domain.retrofit.NewsApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RepositoryTest {

    private val api: NewsApi = Retrofit.Builder()
        .baseUrl("https://93050436-ffb7-4d1b-adc4-291bc813ba72.mock.pstmn.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsApi::class.java)

    private val repository = NewsRepositoryImpl(api)

    @Test
    fun getWrongAddressResponse() = runBlocking {
        val response = repository.getNews(
            search = "tout",
            from = "0",
            to = "0",
            language = "none",
            sortBy = "popularity",
            apiKey = BuildConfig.API_KEY
        )
        assertEquals(null, response)
    }

    @Test
    fun getNews() = runBlocking {
        val response = repository.getNews(
            search = "tout",
            from = "0",
            to = "0",
            language = "fr",
            sortBy = "popularity",
            apiKey = BuildConfig.API_KEY
        )
        assertEquals(3,response?.articles?.size)
    }
}