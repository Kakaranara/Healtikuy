package com.kocci.healtikuy.core.constant

object GameRules {
    /**
     * Max status points are calculated based on a journal about forming a habit.
     * It needs 66 days to form a habit.
     * We need to complete all the mission, and the optimal points got is between 2000.
     * so 2000 * 66 = 132k.
     */
    const val MAX_STATUS_POINTS: Long = 132000
    const val FIRST_TIME_POINTS: Long = (0.4 * MAX_STATUS_POINTS).toLong()
    const val STATUS_COMPLETE_PERCENTAGE: Int = 100
    const val GOALS_WATER_INTAKE = 8
}