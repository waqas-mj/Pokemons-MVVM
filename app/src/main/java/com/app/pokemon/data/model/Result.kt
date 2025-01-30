package com.app.pokemon.data.model

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val code: Int?, val message: String) : Result<Nothing>()
}