package com.stephenw.catmemegenerator.Utils


sealed class Resource<out T : Any> {
    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Error(val error: Exception?, val errorMsg: String) : Resource<Nothing>()
    // Loading for progress bar?
}
