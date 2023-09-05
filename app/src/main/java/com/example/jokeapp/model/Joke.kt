package com.example.jokeapp.model

import com.google.gson.annotations.SerializedName
import retrofit2.http.Url

data class Joke(
    @SerializedName("value")val textJoke: String,
    @SerializedName("icon_url")val iconUrl: String
)
