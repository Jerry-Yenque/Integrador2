package com.grof.integrator.domain.model

import com.google.gson.Gson

data class User (
    var id: String = "",
    var username: String = "",  // To evaluate
    var email: String = "",
    var password: String = ""
) {
    fun toJson(): String = Gson().toJson(this)

    companion object {
        fun fromJson(data: String): User = Gson().fromJson(data, User::class.java)
    }
}