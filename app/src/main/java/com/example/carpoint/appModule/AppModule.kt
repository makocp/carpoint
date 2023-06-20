package com.example.carpoint.appModule

import com.example.carpoint.authentication.AuthRepositoryImpl
import com.example.carpoint.authentication.IAuthentication
import com.example.carpoint.dataBase.IDatabaseHandler
import com.example.carpoint.dataBase.IDatabaseHandlerImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger module responsible for providing dependencies related to the application module.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides an instance of [FirebaseAuth] for Firebase authentication.
     *
     * @return An instance of [FirebaseAuth].
     */
    @Provides
    @Singleton
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()




    /**
     * Provides an implementation of [IAuthentication] interface using [AuthRepositoryImpl].
     *
     * @param firebaseAuth The instance of [FirebaseAuth] for authentication.
     * @return An instance of [AuthRepositoryImpl] implementing [IAuthentication].
     */
    @Provides
    @Singleton
    fun providesRepositoryImpl(firebaseAuth: FirebaseAuth): IAuthentication {
        return AuthRepositoryImpl(firebaseAuth)
    }


    @Provides
    @Singleton
    fun providesFirebaseDatabase() = FirebaseDatabase.getInstance()

    @Provides
    @Singleton
    fun providesDatabaseImpl(firebaseDatabase: FirebaseDatabase): IDatabaseHandler {
        return IDatabaseHandlerImpl(firebaseDatabase)
    }

}
