package com.example.retrofit

import retrofit2.Response
import retrofit2.http.GET

interface JokesINterface {
    @GET("/random_joke")
   suspend fun getJoke() :  Response<jokes>
}