package fr.lleotraas.newsapp.presentation.utils

import java.text.SimpleDateFormat
import java.util.*

object Utils {


    /**
     * Return the today date in yyy-MM-dd format.
     */

    fun getYesterdayDate(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, -1)
        return SimpleDateFormat("yyyy-MM-dd", Locale.US).format(calendar.time)
    }

    fun getTodayDate(): String{
        val calendar = Calendar.getInstance()
        return SimpleDateFormat("yyyy-MM-dd", Locale.US).format(calendar.time)
    }
}