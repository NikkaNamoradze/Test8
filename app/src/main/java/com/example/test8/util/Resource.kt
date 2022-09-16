package com.example.test8.util


sealed class Resource<T>(val data: T? = null, val errorMessage: String? = null) {
    class Success<T>(data: T? = null) : Resource<T>(data)
    class Failure<T>(errorMessage: String, data: T? = null) : Resource<T>(data, errorMessage)
    class Loading<T> : Resource<T>()
}