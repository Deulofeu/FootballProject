package com.example.footballproject

sealed class Result<out Any> {
    data class Success<Any>(val data: Any) : Result<Any>()
}