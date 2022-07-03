package com.akane.scarletserenity.service

import com.akane.scarletserenity.model.webservice.CharacterGame
import com.akane.scarletserenity.model.webservice.User
import retrofit2.Response
import retrofit2.http.*

interface ApiCharacterGameService {

    @GET("charactergames/_all")
    suspend fun getCharacterGames(): Response<MutableList<CharacterGame>>

    @GET("charactergames/{pseudo}")
    suspend fun getCharacterGameByPseudo(@Path("pseudo") num : String): Response<CharacterGame>

    @GET("charactergames/user/{username}")
    suspend fun getCharacterGameByUser(@Path("username") num : String): Response<CharacterGame>

    @POST("charactergames")
    suspend fun createCharacterGame(@Body characterGame: CharacterGame): Response<CharacterGame>

    @PUT("charactergames/{pseudo}/money")
    suspend fun transactionMoney(@Path("pseudo") num: String, @Query("amount") valueIncrement :Int) :Response<Unit>

    @PUT("charactergames/{pseudo}/hpmax")
    suspend fun incrementHpMaxCharacter(@Path("pseudo") num: String, @Query("valueIncrement") valueIncrement: Int) :Response<Unit>

    @PUT("charactergames/{pseudo}/manamax")
    suspend fun incrementManaMaxCharacter(@Path("pseudo") num: String, @Query("valueIncrement") valueIncrement :Int) :Response<Unit>

    @PUT("charactergames/{pseudo}/staminamax")
    suspend fun incrementStaminaMaxCharacter(@Path("pseudo") num: String, @Query("valueIncrement") valueIncrement :Int) :Response<Unit>

    @PUT("charactergames/{pseudo}/hungermax")
    suspend fun incrementHungerMaxCharacter(@Path("pseudo") num: String, @Query("valueIncrement") valueIncrement :Int) :Response<Unit>

    @PUT("charactergames/{pseudo}/hp")
    suspend fun incrementHpCharacter(@Path("pseudo") num: String, @Query("valueIncrement") valueIncrement :Int) :Response<Unit>

    @PUT("charactergames/{pseudo}/mana")
    suspend fun incrementManaCharacter(@Path("pseudo") num: String, @Query("valueIncrement") valueIncrement :Int) :Response<Unit>

    @PUT("charactergames/{pseudo}/stamina")
    suspend fun incrementStaminaCharacter(@Path("pseudo") num: String, @Query("valueIncrement") valueIncrement :Int) :Response<Unit>

    @PUT("charactergames/{pseudo}/hunger")
    suspend fun incrementHungerCharacter(@Path("pseudo") num: String, @Query("valueIncrement") valueIncrement :Int) :Response<Unit>

    @PUT("charactergames/{pseudo}/strength")
    suspend fun incrementStrengthCharacter(@Path("pseudo") num: String, @Query("valueIncrement") valueIncrement :Int) :Response<Unit>

    @PUT("charactergames/{pseudo}/intelligence")
    suspend fun incrementIntelligenceCharacter(@Path("pseudo") num: String, @Query("valueIncrement") valueIncrement :Int) :Response<Unit>

    @PUT("charactergames/{pseudo}/agility")
    suspend fun incrementAgilityCharacter(@Path("pseudo") num: String, @Query("valueIncrement") valueIncrement :Int) :Response<Unit>

    @PUT("charactergames/{pseudo}/luck")
    suspend fun incrementLuckCharacter(@Path("pseudo") num: String, @Query("valueIncrement") valueIncrement :Int) :Response<Unit>

    @PUT("charactergames/{pseudo}/lastlogin")
    suspend fun lastLoginCharacter(@Path("pseudo") num: String, @Query("datetime") datetime :String) :Response<Unit>

}