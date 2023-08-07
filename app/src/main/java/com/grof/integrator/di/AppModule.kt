package com.grof.integrator.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.grof.integrator.core.Constants.USERS
import com.grof.integrator.data.repository.AuthRepositoryImpl
import com.grof.integrator.data.repository.UsersRepositoryImpl
import com.grof.integrator.domain.repository.AuthRepository
import com.grof.integrator.domain.repository.UsersRepository
import com.grof.integrator.domain.use_cases.auth.AuthUseCases
import com.grof.integrator.domain.use_cases.auth.GetCurrentUser
import com.grof.integrator.domain.use_cases.auth.Login
import com.grof.integrator.domain.use_cases.auth.Logout
import com.grof.integrator.domain.use_cases.auth.SignUp
import com.grof.integrator.domain.use_cases.users.Create
import com.grof.integrator.domain.use_cases.users.GetUserById
import com.grof.integrator.domain.use_cases.users.UsersUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore
    @Provides
    fun provideUsersRef(db: FirebaseFirestore): CollectionReference = db.collection(USERS)
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideUsersRepository(impl: UsersRepositoryImpl): UsersRepository = impl
    @Provides
    fun provideAuthUseCases(repository: AuthRepository) = AuthUseCases(
        getCurrentUser = GetCurrentUser(repository),
        login = Login(repository),
        logout = Logout(repository),
        signup = SignUp(repository)
    )

    @Provides
    fun provideUsersUseCases(repository: UsersRepository) = UsersUseCases(
        create = Create(repository),
        getUserById = GetUserById(repository)
    )
}