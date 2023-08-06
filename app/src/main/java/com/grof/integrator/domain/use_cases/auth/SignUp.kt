package com.grof.integrator.domain.use_cases.auth

import com.grof.integrator.domain.model.User
import com.grof.integrator.domain.repository.AuthRepository
import javax.inject.Inject

class SignUp @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(user: User) = repository.signuUp(user)
}