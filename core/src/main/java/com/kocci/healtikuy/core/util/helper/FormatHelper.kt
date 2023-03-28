package com.kocci.healtikuy.core.util.helper

object FormatHelper {
    fun pad2StartForTime(time: Int): String {
        return if (time < 10) {
            time.toString().padStart(2, '0')
        } else {
            time.toString()
        }
    }
}