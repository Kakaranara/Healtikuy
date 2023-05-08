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
        val foodTemp = mutableListOf<String>()
        list.forEach { nutrition ->
            val dateString = DateHelper.formatDateString(nutrition.unixTimestamp * 1000)
            titleTemp.add(dateString)
            foodTemp.add(nutrition.foodName)
        }

        groupList.addAll(titleTemp)
        groupList.forEach {
            itemList[it] = foodTemp
        }

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