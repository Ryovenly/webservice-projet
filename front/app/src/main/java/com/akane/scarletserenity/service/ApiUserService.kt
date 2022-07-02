package com.akane.scarletserenity.service

import com.akane.scarletserenity.model.webservice.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiUserService {

    @GET("users/_all")
    suspend fun getUsers(): Response<MutableList<User>>

    @GET("users/{username}")
    suspend fun getUserByUsername(@Path("username") num : String): Response<User>

    @POST("users")
    suspend fun createUser(@Body user: User): Response<Unit>

}