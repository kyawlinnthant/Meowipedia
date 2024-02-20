package com.everest.datastore

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppDataStoreModule {
    @Binds
    @Singleton
    fun bindAppDataStoreRepo(repo: AppDataStoreImpl): AppDataStore
}
