package com.example.retrofit

import retrofit2.Response
import retrofit2.http.GET

interface GreatThoughInterface {
    @GET("/api/random")
    suspend fun newQuote() : Response<ArrayList<greatThoughtsItem>>
}