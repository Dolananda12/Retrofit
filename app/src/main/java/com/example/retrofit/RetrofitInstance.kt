package com.example.retrofit

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance(mode : Int){
    companion object{
        val BASE_URL_1 = "https://official-joke-api.appspot.com"
        val BASE_URL_2 = "https://zenquotes.io"
        fun getRetrofitInstance_1():Retrofit{
            return Retrofit.Builder().baseUrl(BASE_URL_1)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
        fun getRetrofitInstance_2():Retrofit{
            return Retrofit.Builder().baseUrl(BASE_URL_2)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
    }
}