package com.wil8dev.labrocante.view.core

import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CommonModule {

    @Provides
    @Singleton
    @Named("firebaseAuth")
    fun providesFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    @Named("userId")
    fun providesUserId(
        @Named("firebaseAuth")
        firebaseAuth: FirebaseAuth,
    ): String = firebaseAuth.currentUser?.uid ?: ""

    @Provides
    @Singleton
    @Named("firebaseAnalytics")
    fun providesFirebaseAnalytics(): FirebaseAnalytics = Firebase.analytics
}
