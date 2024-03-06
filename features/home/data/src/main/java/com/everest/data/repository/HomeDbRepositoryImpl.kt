package com.everest.data.repository

import com.everest.database.db.MeowDatabase
import com.everest.database.entity.meow.MeowEntity
import com.everest.database.map.MeowWithBreeds
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeDbRepositoryImpl @Inject constructor(
    private val db: MeowDatabase
) : HomeDbRepository {
    override suspend fun saveMeow(meow: MeowEntity) {
        db.meowDao().insertMeow(meow)
    }

    override suspend fun saveMeows(meows: List<MeowEntity>) {
        db.meowDao().insertMeows(meows)
    }

    override suspend fun getMeowById(meowId: String): Flow<MeowWithBreeds> {
        return db.meowDao().getMeowById(meowId)
    }
}
