package com.akane.scarletserenity.service

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BasicAuthClient<T>(var username:String?, var password:String?) {

    private val client =  OkHttpClient.Builder()
        .addInterceptor(BasicAuthInterceptor(username!!, password!!))
        .build()

    val gson = GsonBuilder()
        .setLenient()
        .create();

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.99.105:8080")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun create(service: Class<T>): T {
        return retrofit.create(service)
    }
}