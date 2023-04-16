package com.example.footballproject.domain.leagues

data class CompetitionView(
    val code: String,
    var emblem: String,
    val id: Int,
    var name: String,
    var type: String
)