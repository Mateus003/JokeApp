package com.example.jokeapp.model.data

import com.example.jokeapp.model.Joke
import com.example.jokeapp.model.callback.JokeCallback
import com.example.jokeapp.model.network.ChuckNorrisAPI
import com.example.jokeapp.model.network.HTTPClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException

class JokeRemoteDataSource() {
    fun findJoke(categoryName:String, callback: JokeCallback){
        HTTPClient.retrofit()
            .create(ChuckNorrisAPI::class.java)
            .findJoke(categoryName)
            .enqueue(object : Callback<Joke>{
                override fun onResponse(call: Call<Joke>, response: Response<Joke>) {
                    if (response.isSuccessful){
                        val joke = response.body()
                        callback.onSuccess(joke ?: throw RuntimeException("Piada não encontrada"))
                    }else{
                        val error = response?.errorBody()?.string()
                        callback.onError(error ?: "Erro desconhecido")
                    }

                    callback.onComplete()

                }

                override fun onFailure(call: Call<Joke>, t: Throwable) {
                    callback.onError(t.message?: "Erro interno")
                    callback.onComplete()
                }

            })
    }

    fun findJokeDay(callback: JokeCallback){
        HTTPClient.retrofit()
            .create(ChuckNorrisAPI::class.java)
            .findJokeDay()
            .enqueue(object :Callback<Joke>{
                override fun onResponse(call: Call<Joke>, response: Response<Joke>) {
                    if (response.isSuccessful){
                        val  joke = response.body()
                        callback.onSuccess(joke?: throw RuntimeException("Piada não encontrada"))
                    }else{
                        val error = response.errorBody()?.string()
                        callback.onError(error?: "Erro desconhecido")
                    }
                    callback.onComplete()
                }

                override fun onFailure(call: Call<Joke>, t: Throwable) {
                    callback.onError(t.message ?: "Erro interno")
                    callback.onComplete()
                }

            })
    }

}