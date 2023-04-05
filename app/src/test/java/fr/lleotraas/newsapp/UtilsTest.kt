package fr.lleotraas.newsapp

import fr.lleotraas.newsapp.presentation.utils.Utils
import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat
import java.util.*

class UtilsTest {
    @Test
    fun `get today date formatted`() {
        val calendar = Calendar.getInstance()
        val todayDate = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(calendar.time)
        val dateToTest = Utils.getTodayDate()
        assertEquals(todayDate, dateToTest)
    }

    @Test fun `get yesterday date formatted`() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, -1)
        val dateExpected = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(calendar.time)
        val dateToTest = Utils.getYesterdayDate()
        assertEquals(dateExpected, dateToTest)
    }
}