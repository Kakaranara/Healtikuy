package com.kocci.healtikuy.util.helper

import com.kocci.healtikuy.core.domain.model.Sleep
import com.kocci.healtikuy.core.domain.model.WaterIntake
import com.kocci.healtikuy.core.domain.model.exercise.Jogging
import com.kocci.healtikuy.core.domain.model.exercise.Running
import com.kocci.healtikuy.core.domain.model.exercise.StaticBike
import com.kocci.healtikuy.core.domain.model.history.HistoryList
import com.kocci.healtikuy.core.util.helper.DateHelper

object HistoryHelper {

//    fun orchestrateWaterIntake(list: List<WaterIntake>): HistoryList {
//        val groupList = mutableListOf<String>()
//        val itemList = hashMapOf<String, List<String>>()
//
//        list.forEach { water ->
//
//        }
//    }

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
                    "Set : ${it.set} Second",
                    "Interval : ${it.interval} Meter",
                    "Rest Time : ${it.restTime} Seconds"
                )
                itemList[title] = child
            }
        }

        return HistoryList(groupList, itemList)
    }
}