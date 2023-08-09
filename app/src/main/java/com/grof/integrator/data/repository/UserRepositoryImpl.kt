package com.grof.integrator.data.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.grof.integrator.domain.model.Response
import com.grof.integrator.domain.model.User
import com.grof.integrator.core.Constants.USERS
import com.grof.integrator.domain.repository.UserRepository
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

class  UserRepositoryImpl @Inject constructor(private val usersRef: CollectionReference): UserRepository {

    override suspend fun create(user: User): Response<Boolean> {
        return try{
            usersRef.document(user.id).set(user).await()
            Response.Success(true)
        } catch (e: Exception){
            e.printStackTrace()
            Response.Failure(e)
        }

    }

}