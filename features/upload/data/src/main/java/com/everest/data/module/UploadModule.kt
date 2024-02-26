package com.everest.data.module

import com.everest.data.repository.UploadFileRepo
import com.everest.data.repository.UploadFileRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UploadModule {

    @Binds
    @Singleton
    fun bindUploadFileRepo(repo: UploadFileRepoImpl): UploadFileRepo
}
