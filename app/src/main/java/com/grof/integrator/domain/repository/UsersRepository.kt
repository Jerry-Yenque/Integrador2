package com.grof.integrator.domain.repository

import com.grof.integrator.domain.model.Response
import com.grof.integrator.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    suspend fun create(user: User): Response<Boolean>
    fun getUserById(id: String): Flow<User>
}