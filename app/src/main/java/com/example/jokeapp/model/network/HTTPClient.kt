package com.example.jokeapp.model.network

import com.example.jokeapp.util.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileInputStream
import java.util.*

object HTTPClient {

    private fun httpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }


    fun retrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL) //Url base da API
            .addConverterFactory(GsonConverterFactory.create())//Converte a estrutura JSON para um model
            .client(httpClient())
            .build()
    }


}