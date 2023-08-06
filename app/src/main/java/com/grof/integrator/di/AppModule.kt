package com.grof.integrator.di

import com.google.firebase.auth.FirebaseAuth
import com.grof.integrator.data.repository.AuthRepositoryImpl
import com.grof.integrator.domain.repository.AuthRepository
import com.grof.integrator.domain.use_cases.auth.AuthUseCases
import com.grof.integrator.domain.use_cases.auth.GetCurrentUser
import com.grof.integrator.domain.use_cases.auth.Login
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideAuthUseCases(repository: AuthRepository) = AuthUseCases(
        getCurrentUser = GetCurrentUser(repository),
        login = Login(repository)
    )
}