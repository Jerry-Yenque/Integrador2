package com.grof.integrator.domain.repository

import com.google.firebase.firestore.auth.User
import com.grof.integrator.domain.model.Response

interface UserRepository {

    suspend fun create(user:User): Response<Boolean>
}