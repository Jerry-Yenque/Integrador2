package com.grof.integrator.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.grof.integrator.domain.model.Response
import com.grof.integrator.domain.model.User

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Response<FirebaseUser>
    suspend fun signuUp(user: User): Response<FirebaseUser>
    fun logout()
}