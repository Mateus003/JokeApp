package com.example.jokeapp.model.data

import com.example.jokeapp.model.callback.ListCategoryCallback
import com.example.jokeapp.model.network.ChuckNorrisAPI
import com.example.jokeapp.model.network.HTTPClient
import com.example.jokeapp.util.Constants.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryRemoteDataSource{
    fun findAllCategories(callback: ListCategoryCallback){
        HTTPClient.retrofit()
            .create(ChuckNorrisAPI::class.java)
            .findAllCategories(API_KEY)
            .enqueue(/* callback = */ object : Callback<List<String>>{
                override fun onResponse(
                    call: Call<List<String>>,
                    response: Response<List<String>>
                ) {
                    if (response.isSuccessful){
                        val categories = response.body()
                        callback.onSuccess(categories ?: emptyList())
                    }else{
                        val error = response.errorBody()?.string()

                        if (error != null) {
                            callback.onError(error)
                        }

                    }
                    callback.onComplete()
                }

                override fun onFailure(call: Call<List<String>>, t: Throwable) {
                    callback.onError(t.message?: "Erro interno")
                    callback.onComplete()
                }

            }


            )


    }

}
