package com.example.footballproject

import javax.inject.Inject

class DateConverter @Inject constructor() {

    fun getDate(dateString: String): String {
        val date = dateString.split("T")[0].split("-")
        var newDate = ""
        for (i in date.indices.reversed()) {
            newDate += if (i != 0) {
                (date[i] + "-")
            } else {
                (date[i])
            }
        }
        return newDate
    }
}