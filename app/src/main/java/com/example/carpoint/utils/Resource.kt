package com.example.carpoint.utils


sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class success<T>(data: T) : Resource<T>(data)
    class error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class loading<T>(data: T? = null) : Resource<T>(data)
}