package com.everest.data.module

import com.everest.data.service.GalleryApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GalleryApiModule {

    @Provides
    @Singleton
    fun providesGalleryApi(
        retrofit: Retrofit
    ) : GalleryApi = retrofit.create(GalleryApi::class.java)
}
