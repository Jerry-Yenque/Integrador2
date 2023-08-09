package com.grof.integrator.domain.use_cases.users

import com.grof.integrator.domain.model.User
<<<<<<< HEAD
import com.grof.integrator.domain.repository.UserRepository
import javax.inject.Inject

class Create @Inject constructor(private val repository: UserRepository){
        suspend operator fun invoke(user: User) = repository.create(user)
=======
import com.grof.integrator.domain.repository.UsersRepository
import javax.inject.Inject

class Create @Inject constructor(private val repository: UsersRepository){
    suspend operator fun invoke(user: User) = repository.create(user)
>>>>>>> 01ae4c61941f87435dd7b0f27f9382e8d1106175
}