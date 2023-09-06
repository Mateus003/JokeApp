package com.example.jokeapp.model.callback

import com.example.jokeapp.model.Joke

interface JokeCallback {
    fun onSuccess(response: Joke)

    fun onError(response: String)

    fun onComplete()

}