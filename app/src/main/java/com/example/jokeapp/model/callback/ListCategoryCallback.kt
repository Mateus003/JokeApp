package com.example.jokeapp.model.callback

interface ListCategoryCallback {
    fun onSuccess(response: List<String>)

    fun onError(response: String)

    fun onComplete()


}