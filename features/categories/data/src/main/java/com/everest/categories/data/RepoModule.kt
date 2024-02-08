package com.everest.categories.data

import com.everest.categories.data.repository.CategoriesRepo
import com.everest.categories.data.repository.CategoriesRepoImpl
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
}
