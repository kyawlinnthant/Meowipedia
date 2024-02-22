package com.everest.data.repository

import com.everest.database.dao.MeowDao
import com.everest.database.entity.MeowEntity
import javax.inject.Inject

class HomeDbRepositoryImpl @Inject constructor(
    private val meowDao: MeowDao
) : HomeDbRepository {
    override suspend fun saveMeow(meowEntity: MeowEntity) {
        meowDao.insertMeow(meowEntity)
    }
}
