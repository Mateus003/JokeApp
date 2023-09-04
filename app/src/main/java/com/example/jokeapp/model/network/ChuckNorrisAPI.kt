package com.example.jokeapp.model.network

import com.example.jokeapp.model.network.HTTPClient
import com.example.jokeapp.util.Constants.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ChuckNorrisAPI {
    @GET("jokes/categories")
    fun findAllCategories(@Query("apiKey")apiKey: String = API_KEY): Call<List<String>>

}