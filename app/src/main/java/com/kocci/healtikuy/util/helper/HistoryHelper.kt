package com.kocci.healtikuy.util.helper

import com.kocci.healtikuy.core.domain.model.Nutrition
import com.kocci.healtikuy.core.domain.model.Sleep
import com.kocci.healtikuy.core.domain.model.exercise.Jogging
import com.kocci.healtikuy.core.domain.model.exercise.Running
import com.kocci.healtikuy.core.domain.model.exercise.StaticBike
import com.kocci.healtikuy.core.domain.model.history.HistoryList
import com.kocci.healtikuy.core.util.helper.DateHelper

object HistoryHelper {

    fun orchestrateNutrition(list: List<Nutrition>): HistoryList {
        val groupList = mutableListOf<String>()
        val itemList = hashMapOf<String, List<String>>()

        val titleTemp = mutableSetOf<String>()
        val itemTemp = mutableListOf<String>()
        var lastDate: String = DateHelper.formatDateString(list[0].unixTimestamp * 1000)
        list.forEachIndexed { index, nutrition ->
            val dateString = DateHelper.formatDateString(nutrition.unixTimestamp * 1000)
            if (dateString != lastDate) {
                itemList[lastDate] = itemTemp
                lastDate = dateString
                itemTemp.clear()
            }
            titleTemp.add(dateString)
            itemTemp.add(nutrition.foodName)
            if (index == list.lastIndex) {
                itemList[lastDate] = itemTemp
            }
        }
        groupList.addAll(titleTemp)
//        groupList.forEach { title ->
//            val itemForGroups = list.filter {
//                val dateInString = DateHelper.formatDateString(it.unixTimestamp * 1000)
//                dateInString == title
//            }.map { it.foodName }
//            itemList[title] = itemForGroups
//        }

        return HistoryList(groupList, itemList)
    }

    fun orchestrateSleep(list: List<Sleep>): HistoryList {
        val groupList = mutableListOf<String>()
        val itemList = hashMapOf<String, List<String>>()

        list.forEach { sleep ->
            if (sleep.isCompleted) {
                val title = DateHelper.formatDateString(dateInMillis = sleep.timeCompleted)
                groupList.add(title)
                val child = listOf<String>(
                    "Sleep Time : ${DateHelper.showHoursAndMinutes(sleep.timeCompleted)}",
                )
                itemList[title] = child
            }
        }
        return HistoryList(groupList, itemList)
    }

    fun orchestrateJogging(list: List<Jogging>): HistoryList {
        val groupList = mutableListOf<String>()
        val itemList = hashMapOf<String, List<String>>()

        list.forEach { jogging ->
            if (jogging.isCompleted) {
                val title = DateHelper.formatDateString(dateInMillis = jogging.timeCompleted)
                groupList.add(title)
                val child = listOf<String>(
                    "Duration : ${jogging.duration} Second",
                    "Mileage : ${jogging.distance} Meter"
                )
                itemList[title] = child
            }
        }
        return HistoryList(groupList, itemList)
    }

    fun orchestrateRunning(list: List<Running>): HistoryList {
        val groupList = mutableListOf<String>()
        val itemList = hashMapOf<String, List<String>>()

        list.forEach { running ->
            if (running.isCompleted) {
                val title = DateHelper.formatDateString(dateInMillis = running.timeCompleted)
                groupList.add(title)
                val child = listOf<String>(
                    "Duration : ${running.duration} Second",
                    "Mileage : ${running.distance} Meter"
                )
                itemList[title] = child
            }
        }
        return HistoryList(groupList, itemList)
    }

    fun orchestrateStaticBike(list: List<StaticBike>): HistoryList {
        val groupList = mutableListOf<String>()
        val itemList = hashMapOf<String, List<String>>()

        list.forEach {
            if (it.isCompleted) {
                val title = DateHelper.formatDateString(dateInMillis = it.timeCompleted)
                groupList.add(title)
                val child = listOf<String>(
                    "Set : ${it.set} Set",
                    "Interval : ${it.interval} Meter",
                    "Rest Time : ${it.restTime} Seconds"
                )
                itemList[title] = child
            }
        }

        return HistoryList(groupList, itemList)
    }
}