package com.grof.integrator.domain.use_cases.users

import com.grof.integrator.domain.model.User
import com.grof.integrator.domain.repository.UserRepository
import javax.inject.Inject

class Create @Inject constructor(private val repository: UserRepository){
        suspend operator fun invoke(user: User) = repository.create(user)
}