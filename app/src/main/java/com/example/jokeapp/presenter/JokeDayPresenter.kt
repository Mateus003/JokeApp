package com.example.jokeapp.presenter

import com.example.jokeapp.model.Joke
import com.example.jokeapp.model.callback.JokeCallback
import com.example.jokeapp.model.data.JokeRemoteDataSource
import com.example.jokeapp.view.fragments.JokeDayFragment
import com.example.jokeapp.view.fragments.JokeFragment

class JokeDayPresenter (private val view: JokeDayFragment,
                        private val dataSource: JokeRemoteDataSource = JokeRemoteDataSource()
): JokeCallback {
    fun findBy(){
        dataSource.findJokeDay(this)
    }

    override fun onSuccess(response: Joke) {
        view.showJoke(response)
    }

    override fun onError(response: String) {
        view.showFailure(response)
    }

    override fun onComplete() {
        view.hideProgressBar()
    }
}