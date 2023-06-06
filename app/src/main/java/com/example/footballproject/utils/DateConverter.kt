package com.example.footballproject.utils

import javax.inject.Inject

class DateConverter @Inject constructor() {

    fun getDate(dateString: String): String {
        val date = dateString.split("T")[0].split("-")
        val timeUTC =
            dateString.substring(dateString.lastIndexOf('T') + 1, dateString.lastIndexOf(":00Z"))
        val time = timeUTC.split(":")

        val currentDay: String = when (time[0]) {
            "21" -> (date[2].toInt() + 1).toString()
            "22" -> (date[2].toInt() + 1).toString()
            "23" -> (date[2].toInt() + 1).toString()
            else -> date[2]
        }
        val newDate = currentDay + "." + date[1] + "." + date[0]

        val timeHour: String = when (time[0]) {
            "21" -> "00"
            "22" -> "1"
            "23" -> "2"
            "00" -> "3"
            else -> (time[0].toInt() + 3).toString()
        }

        return newDate + " " + timeHour + ":" + time[1]
    }
}