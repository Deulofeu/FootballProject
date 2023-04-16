package com.example.footballproject

sealed class Result<out Any> {
    data class Error(val exception: Exception) : Result<Nothing>()
    data class Success<Any>(val data: Any) : Result<Any>()
}