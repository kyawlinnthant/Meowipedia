package com.everest.data.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface HomeRepoModule {
    @Binds
    @Singleton
    fun bindApiRepo(repo: HomeApiRepositoryImpl): HomeApiRepository

    @Binds
    @Singleton
    fun bindDbRepo(repo: HomeDbRepositoryImpl): HomeDbRepository
}
