package com.grof.integrator.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.grof.integrator.core.Constants.USERS
import com.grof.integrator.data.repository.AuthRepositoryImpl
<<<<<<< HEAD
import com.grof.integrator.data.repository.UserRepositoryImpl
import com.grof.integrator.domain.model.User
import com.grof.integrator.domain.repository.AuthRepository
import com.grof.integrator.domain.repository.UserRepository
=======
import com.grof.integrator.data.repository.UsersRepositoryImpl
import com.grof.integrator.domain.repository.AuthRepository
import com.grof.integrator.domain.repository.UsersRepository
>>>>>>> 01ae4c61941f87435dd7b0f27f9382e8d1106175
import com.grof.integrator.domain.use_cases.auth.AuthUseCases
import com.grof.integrator.domain.use_cases.auth.GetCurrentUser
import com.grof.integrator.domain.use_cases.auth.Login
import com.grof.integrator.domain.use_cases.auth.Logout
import com.grof.integrator.domain.use_cases.auth.SignUp
import com.grof.integrator.domain.use_cases.users.Create
<<<<<<< HEAD
=======
import com.grof.integrator.domain.use_cases.users.GetUserById
import com.grof.integrator.domain.use_cases.users.Update
>>>>>>> 01ae4c61941f87435dd7b0f27f9382e8d1106175
import com.grof.integrator.domain.use_cases.users.UsersUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
<<<<<<< HEAD
    fun provideFirebaseFirestore(): FirebaseFirestore=Firebase.firestore
    @Provides
    fun provideUserRef(db: FirebaseFirestore): CollectionReference = db.collection(USERS)

=======
    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore
    @Provides
    fun provideUsersRef(db: FirebaseFirestore): CollectionReference = db.collection(USERS)
>>>>>>> 01ae4c61941f87435dd7b0f27f9382e8d1106175
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
<<<<<<< HEAD
    fun provideUsersRepository(impl:UserRepositoryImpl): UserRepository =impl


=======
    fun provideUsersRepository(impl: UsersRepositoryImpl): UsersRepository = impl
>>>>>>> 01ae4c61941f87435dd7b0f27f9382e8d1106175
    @Provides
    fun provideAuthUseCases(repository: AuthRepository) = AuthUseCases(
        getCurrentUser = GetCurrentUser(repository),
        login = Login(repository),
        logout = Logout(repository),
        signup = SignUp(repository)
    )

    @Provides
<<<<<<< HEAD
    fun provideUsersUseCase(repository: UserRepository) = UsersUseCases(
        create = Create(repository)
=======
    fun provideUsersUseCases(repository: UsersRepository) = UsersUseCases(
        create = Create(repository),
        getUserById = GetUserById(repository),
        update = Update(repository)
>>>>>>> 01ae4c61941f87435dd7b0f27f9382e8d1106175
    )
}