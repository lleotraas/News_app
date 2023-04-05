package fr.lleotraas.newsapp.domain.use_cases

import fr.lleotraas.newsapp.domain.model.News
import fr.lleotraas.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class GetNews @Inject constructor(
    private val repository: NewsRepository
) {

    suspend operator fun invoke(search: String, from: String, to: String, language: String,sortBy: String, apiKey: String): News? {
        return repository.getNews(search, from, to, language, sortBy, apiKey)
    }

}