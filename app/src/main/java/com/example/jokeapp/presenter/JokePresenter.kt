package com.example.jokeapp.presenter

import com.example.jokeapp.model.Category
import com.example.jokeapp.model.Joke
import com.example.jokeapp.model.callback.JokeCallback
import com.example.jokeapp.model.data.JokeRemoteDataSource
import com.example.jokeapp.view.fragments.JokeFragment

class JokePresenter(private val view:JokeFragment,
            private val dataSource:JokeRemoteDataSource = JokeRemoteDataSource()):JokeCallback {
    fun findBy(categoryName:String){
        dataSource.findJoke(categoryName, this)
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