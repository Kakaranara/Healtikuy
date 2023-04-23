package com.kocci.healtikuy.util.helper

import com.kocci.healtikuy.core.domain.model.exercise.Jogging
import com.kocci.healtikuy.core.domain.model.history.HistoryList
import com.kocci.healtikuy.core.util.helper.DateHelper

object HistoryHelper {
    fun orchestrateJogging(list: List<Jogging>): HistoryList {
        val groupList = mutableListOf<String>()
        val itemList = hashMapOf<String, List<String>>()

        list.forEach { jogging ->
            val title = DateHelper.formatDateString(dateInMillis = jogging.timeCompleted)
            groupList.add(title)
            val child = listOf<String>(
                "Duration : ${jogging.duration} Second",
                "Mileage : ${jogging.distance} Meter"
            )
            itemList[title] = child
        }
        return HistoryList(groupList, itemList)
    }
}