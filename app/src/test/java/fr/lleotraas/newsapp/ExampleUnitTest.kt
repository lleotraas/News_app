package fr.lleotraas.newsapp

import fr.lleotraas.newsapp.presentation.utils.Utils
import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UtilsTest {
    @Test
    fun getTodayDateFormatted() {
        val calendar = Calendar.getInstance()
        val todayDate = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(calendar.time)
        assertEquals(todayDate, Utils.getTodayDate())
    }

    @Test fun getYesterdayDateFormatted() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, -1)
        val yesterday = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(calendar.time)
        assertEquals(yesterday, Utils.getYesterdayDate())
    }
}