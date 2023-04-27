package com.kocci.healtikuy.core

import com.kocci.healtikuy.core.util.helper.DateHelper
import org.junit.Assert
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*


class DateTest {

    private val dateFormat = SimpleDateFormat("dd/MM/yyyy")
    private val timeFormat = SimpleDateFormat(DateHelper.TIME_FORMAT)
    private val dateTimeFormat = SimpleDateFormat(DateHelper.FULL_DATE_FORMAT)
    private val yesterday = "22/3/2023"
    private val tomorrow = "24/3/2023"
    private val example = "4/4/2023"
    private val timeExample = "18:00"


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

    @Test
    fun `Time String convert should be equals with long`() {
        val time = DateHelper.convertTimeStringToLong(timeExample)
        val actual = timeFormat.parse(timeExample).time

        val cal = Calendar.getInstance()
        val cal2 = Calendar.getInstance()
        cal2.time = Date(time)
        cal.apply {
            set(Calendar.HOUR_OF_DAY, cal2.get(Calendar.HOUR_OF_DAY))
            set(Calendar.MINUTE, cal2.get(Calendar.MINUTE))
        }
        "dd/MM/yyyy, HH:mm"
        val formattedTime = dateTimeFormat.format(cal.time)

        Assert.assertEquals(formattedTime, "2222")
    }
}