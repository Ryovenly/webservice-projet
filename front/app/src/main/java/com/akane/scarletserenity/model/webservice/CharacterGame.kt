package com.akane.scarletserenity.model.webservice

data class CharacterGame(
    val pseudo: String? = null,
    val gender: String? = null,
    val money: Long? = null,
    val hpMax: Long? = null,
    val manaMax: Long? = null,
    val staminaMax: Long? = null,
    val hungerMax: Long? = null,
    val hp: Long? = null,
    val mana: Long? = null,
    val stamina: Long? = null,
    val hunger: Long? = null,
    val strength: Long? = null,
    val intelligence: Long? = null,
    val agility: Long? = null,
    val luck: Long? = null,
    val created: String? = null,
    val lastLogin: String? = null,
    val img: String? = null,
    val user:User?
)
