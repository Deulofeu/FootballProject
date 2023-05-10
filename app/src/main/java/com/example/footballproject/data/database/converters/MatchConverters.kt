package com.example.footballproject.data.database.converters

import androidx.room.TypeConverter
import com.example.footballproject.domain.matches.Competition
import com.example.footballproject.domain.matches.Score
import com.example.footballproject.domain.matches.Team
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MatchConverters {

    private val gson = Gson()

    @TypeConverter
    fun competitionToString(competition: Competition): String {
        return gson.toJson(competition)
    }

    @TypeConverter
    fun stringToCompetition(value: String): Competition {
        val type = object : TypeToken<Competition>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun teamToString(team: Team): String {
        return gson.toJson(team)
    }

    @TypeConverter
    fun stringToTeam(value: String): Team {
        val type = object : TypeToken<Team>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun scoreToString(score: Score): String {
        return gson.toJson(score)
    }

    @TypeConverter
    fun stringToScore(value: String): Score {
        val type = object : TypeToken<Score>() {}.type
        return gson.fromJson(value, type)
    }
}