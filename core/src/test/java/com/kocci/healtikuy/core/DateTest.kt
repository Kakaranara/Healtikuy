package com.kocci.healtikuy.core

import com.kocci.healtikuy.core.util.helper.DateHelper
import org.junit.Assert
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Date


class DateTest {

    private val dateFormat = SimpleDateFormat("dd/MM/yyyy")
    private val yesterday = "22/3/2023"
    private val tomorrow = "24/3/2023"
    private val example = "4/4/2023"

    @Test
    fun test() {
        val yesterdayDate = dateFormat.parse(yesterday) as Date
        val yesterdayTime = yesterdayDate.time

        Assert.assertFalse(DateHelper.isToday(yesterdayTime))
        Assert.assertTrue(DateHelper.isToday(System.currentTimeMillis()))
    }

    @Test
    fun testTime() {
        val time: Long = 1679672771000
        val date = Date(time)
        val str = dateFormat.format(date)

        Assert.assertEquals("24/3/2023", str)
    }

    @Test
    fun testElapsedTime() {
        val beforeTimeExample = dateFormat.parse(example)!!.time
        val elapsedDay = DateHelper.dayTimeElapsed(beforeTimeExample)

        Assert.assertEquals(5, elapsedDay)
    }
}