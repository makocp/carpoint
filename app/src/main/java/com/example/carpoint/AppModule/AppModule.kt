package com.example.carpoint.AppModule

import com.example.carpoint.Authentication.AuthRepositoryImpl
import com.example.carpoint.Authentication.IAuthentication
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
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
}
