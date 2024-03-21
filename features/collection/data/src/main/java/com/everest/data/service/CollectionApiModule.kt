package com.everest.data.service

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class CollectionApiModule {

    @Provides
    @Singleton
    fun provideUploadService(
        retrofit: Retrofit
    ): CollectionService = retrofit.create(CollectionService::class.java)
}
