package com.everest.categories.data.repository

import com.everest.database.dao.CategoryDao
import com.everest.database.entity.CategoryEntity
import com.everest.dispatcher.DispatcherModule
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

class MewoDbRepoImpl @Inject constructor(
    private val mewoDao: CategoryDao,
    @DispatcherModule.IoDispatcher private val io: CoroutineDispatcher
) : MeowRepo {
    override suspend fun saveMeow(categoryEntity: CategoryEntity) {
        mewoDao.insertMeow(categoryEntity)
    }
}
