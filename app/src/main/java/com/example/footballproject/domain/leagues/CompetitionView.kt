package com.example.footballproject.domain.leagues

data class CompetitionView(
    val code: String,
    val emblem: String,
    val id: Int,
    val name: String,
    var type: String
)