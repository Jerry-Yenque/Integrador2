package com.grof.integrator.domain.use_cases.auth

import com.grof.integrator.data.repository.AuthRepositoryImpl
import com.grof.integrator.domain.repository.AuthRepository
import javax.inject.Inject

class Login @Inject constructor(private val repository: AuthRepository){
    suspend operator fun invoke(email: String, password: String) = repository.login(email, password)
}