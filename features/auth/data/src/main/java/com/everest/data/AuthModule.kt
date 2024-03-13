package com.everest.data

import com.everest.data.repo.AuthRepo
import com.everest.data.repo.AuthRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AuthModule {
    @Binds
    @Singleton
    fun provideSignInRepoImpl(repo: AuthRepoImpl): AuthRepo
}
