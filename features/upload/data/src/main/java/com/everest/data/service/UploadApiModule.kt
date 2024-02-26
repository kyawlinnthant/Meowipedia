package com.everest.data.service

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class UploadApiModule {

    @Provides
    @Singleton
    fun provideUploadService(
        retrofit: Retrofit
    ): UploadService = retrofit.create(UploadService::class.java)
}
