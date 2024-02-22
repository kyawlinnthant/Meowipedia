package com.everest.data.service

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class HomeApiModule {

    @Provides
    @Singleton
    fun providesGalleryApi(
        retrofit: Retrofit
    ): HomeApi = retrofit.create(HomeApi::class.java)
}
