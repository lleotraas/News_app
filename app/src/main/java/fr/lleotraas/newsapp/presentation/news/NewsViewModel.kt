package fr.lleotraas.newsapp.presentation.news

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.lleotraas.newsapp.BuildConfig
import fr.lleotraas.newsapp.domain.use_cases.NewsUseCases
import fr.lleotraas.newsapp.presentation.utils.Utils
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val useCases: NewsUseCases
) : ViewModel() {

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
            _state.value = state.value.copy(
                articles = useCases.getNews(search, from, to, language, sortBy, BuildConfig.API_KEY)?.articles ?: emptyList()
            )
        }
    }

}