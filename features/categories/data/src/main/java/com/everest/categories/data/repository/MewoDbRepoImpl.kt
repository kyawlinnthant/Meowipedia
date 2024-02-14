package com.everest.categories.data.repository

import com.everest.database.dao.MeowDao
import com.everest.database.entity.MeowEntity
import com.everest.dispatcher.DispatcherModule
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

class MewoDbRepoImpl @Inject constructor(
    private val mewoDao: MeowDao,
    @DispatcherModule.IoDispatcher private val io: CoroutineDispatcher
) : MeowRepo {
    override suspend fun saveMeow(meowEntity: MeowEntity) {
        mewoDao.insertMeow(meowEntity)
    }
}
