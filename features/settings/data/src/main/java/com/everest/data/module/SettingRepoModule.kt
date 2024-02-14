package com.everest.data.module

import com.everest.data.repository.SettingDsRepo
import com.everest.data.repository.SettingDsRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface SettingRepoModule {

    @Binds
    @Singleton
    fun bindsSettingDsRepo(repo: SettingDsRepoImpl): SettingDsRepo
}
