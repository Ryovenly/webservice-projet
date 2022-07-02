//package com.akane.scarletserenity.service
//
//import com.google.gson.GsonBuilder
//import io.reactivex.Single
//import okhttp3.OkHttpClient
//import retrofit2.Retrofit
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
//import retrofit2.converter.gson.GsonConverterFactory
//
//class PosApiService {
//    private val BASE_URL = "https://api.demo.com"
//    //add BasicAuthInterceptor to OkHttp client
//    val client =  OkHttpClient.Builder()
//        .addInterceptor(BasicAuthInterceptor("demo@demo.com",     "12345678"))
//        .build()
//    val gson = GsonBuilder()
//        .setLenient()
//        .create();
//    // add OkHttp client to Retrofit instance
//    private val api = Retrofit.Builder()
//        .baseUrl(BASE_URL)
//        .client(client)
//        .addConverterFactory(GsonConverterFactory.create(gson))
//        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//        .build()
//
//
//    fun sendAmount(amount: Double, msisdn: String):  Single<PosResponse>{
//        return api.sendAmount(amount, msisdn)
//    }