package com.everest.data.module

import com.everest.data.repository.CollectionRepo
import com.everest.data.repository.CollectionRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CollectionModule {

    @Binds
    @Singleton
    fun bindCollectionRepoImpl(repo: CollectionRepoImpl): CollectionRepo
}
