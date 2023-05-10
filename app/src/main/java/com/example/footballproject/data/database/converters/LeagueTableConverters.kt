package com.example.footballproject.data.database.converters

import androidx.room.TypeConverter
import com.example.footballproject.domain.table.Standing
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LeagueTableConverters {

    private val gson = Gson()

    @TypeConverter
    fun standingToString(standing: List<Standing>): String {
        return gson.toJson(standing)
    }

    @TypeConverter
    fun stringToStanding(value: String): List<Standing> {
        val type = object : TypeToken<List<Standing>>() {}.type
        return gson.fromJson(value, type)
    }
}