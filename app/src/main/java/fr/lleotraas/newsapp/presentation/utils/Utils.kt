package fr.lleotraas.newsapp.presentation.utils

import fr.lleotraas.newsapp.domain.model.Article
import java.text.SimpleDateFormat
import java.util.*

object Utils {


    /**
     * @Return the current date -1 in yyyy-MM-dd format.
     */
    fun getYesterdayDate(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, -1)
        return SimpleDateFormat("yyyy-MM-dd", Locale.US).format(calendar.time)
    }

    /**
     *@return the current date in yyyy-MM-dd format.
     */
    fun getTodayDate(): String{
        val calendar = Calendar.getInstance()
        return SimpleDateFormat("yyyy-MM-dd", Locale.US).format(calendar.time)
    }

    /**
     * @param articles is the list of article loaded when the app start.
     * @param articleTitle is the article title that the user clicked on.
     * @return the article corresponding to the articleTitle in the articles list.
     */
    fun getCurrentArticle(articles: List<Article>, articleTitle: String): Article? {
        var article: Article? = null
        articles.forEach {
            if (it.title == articleTitle) {
                article = it
            }
        }
        return article
    }
}