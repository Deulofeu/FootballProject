package com.example.footballproject.domain.matches

data class Score(
    val halfTime: HalfTime,
    val fullTime: FullTime,
    val winner: String?
)