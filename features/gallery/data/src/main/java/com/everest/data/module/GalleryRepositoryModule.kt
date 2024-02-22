package com.everest.data.module

import com.everest.data.repository.GalleryApiRepository
import com.everest.data.repository.GalleryApiRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface GalleryRepositoryModule {

    @Binds
    @Singleton
    fun bindsGalleryApiRepo(repo: GalleryApiRepositoryImpl): GalleryApiRepository
}
