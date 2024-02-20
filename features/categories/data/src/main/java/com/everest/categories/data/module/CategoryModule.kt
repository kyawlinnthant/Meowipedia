package com.everest.categories.data.module

import com.everest.categories.data.service.CategoriesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object CategoryModule {
    @Provides
    @Singleton
    fun provideCategoriesService(
        retrofit: Retrofit
    ): CategoriesService = retrofit.create(CategoriesService::class.java)
}
