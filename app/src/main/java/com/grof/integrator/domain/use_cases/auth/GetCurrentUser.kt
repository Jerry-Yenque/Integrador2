package com.grof.integrator.domain.use_cases.auth

import com.grof.integrator.domain.repository.AuthRepository
import javax.inject.Inject

class GetCurrentUser @Inject constructor(private val repository: AuthRepository){
    operator fun invoke() = repository.currentUser
}