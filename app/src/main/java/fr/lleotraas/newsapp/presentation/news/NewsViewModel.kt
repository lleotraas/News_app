package fr.lleotraas.newsapp.presentation.news

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.lleotraas.newsapp.BuildConfig
import fr.lleotraas.newsapp.domain.use_cases.NewsUseCases
import fr.lleotraas.newsapp.presentation.utils.Utils
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val useCases: NewsUseCases
) : ViewModel() {

    private val TAG = javaClass.simpleName
    private val _state = mutableStateOf(NewsState())
    val state = _state

    init {

        getNews(
            search = "tout",
            from = Utils.getYesterdayDate(),
            to = Utils.getTodayDate(),
            language = "fr",
            sortBy = "popularity"
        )
    }

    private fun getNews(search: String, from: String, to: String, language: String,sortBy: String) {
        viewModelScope.launch {
            val response = try {
                useCases.getNews(search, from, to, language, sortBy, BuildConfig.API_KEY)
            } catch (e: IOException) {
                Log.e(TAG, "getNews: you may have internet connection, ${e.message}")
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "getNews: Http Exception, unexpected response, ${e.message}")
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                val news = response.body()
                if (news?.status  == "ok") {
                    _state.value = state.value.copy(
                        articles = news.articles
                    )
                    news.articles.forEach {
                        Log.e(TAG, "getNews: ${it.urlToImage} name:${it.author}")
                    }
                }
            }
        }
    }

}