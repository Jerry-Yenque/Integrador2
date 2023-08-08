package com.grof.integrator.domain.use_cases.users

import com.grof.integrator.domain.model.User
import com.grof.integrator.domain.repository.UsersRepository
import javax.inject.Inject

class Update @Inject constructor(private val repository: UsersRepository){
    suspend operator fun invoke(user: User) = repository.update(user)
}