package com.everest.categories.data.module

import com.everest.categories.data.repository.CategoriesRepo
import com.everest.categories.data.repository.CategoriesRepoImpl
import com.everest.categories.data.repository.MeowRepo
import com.everest.categories.data.repository.MewoDbRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepoModule {
    @Binds
    @Singleton
    fun bindCategoryRepo(categoriesRepoImpl: CategoriesRepoImpl): CategoriesRepo

    @Binds
    @Singleton
    fun bindMewoDbRepoImpl(mewoDbRepoImpl: MewoDbRepoImpl): MeowRepo
}
