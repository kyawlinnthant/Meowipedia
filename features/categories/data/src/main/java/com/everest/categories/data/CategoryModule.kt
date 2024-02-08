package com.everest.categories.data

import CategoriesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CategoryModule {
    @Provides
    @Singleton
    fun provideCategoriesService(
        retrofit: Retrofit
    ): CategoriesService = retrofit.create(CategoriesService::class.java)
}
