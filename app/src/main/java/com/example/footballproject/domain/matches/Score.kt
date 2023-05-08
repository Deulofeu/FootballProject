package com.example.footballproject.domain.matches

data class Score(
    val halfTime: Time,
    val fullTime: Time,
    val winner: String?
)