package data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import data.repository.CategoriesRepo
import data.repository.CategoriesRepoImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface RepoModule {
    @Binds
    @Singleton
    fun bindCategoryRepo(categoriesRepoImpl: CategoriesRepoImpl): CategoriesRepo
}
