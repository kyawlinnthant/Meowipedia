package com.everest.data.repository

import com.everest.database.db.MeowDatabase
import com.everest.database.entity.meow.MeowEntity
import javax.inject.Inject

class HomeDbRepositoryImpl @Inject constructor(
    private val db: MeowDatabase
) : HomeDbRepository {

    override suspend fun saveMeows(meows: List<MeowEntity>) {
        db.meowDao().insertMeows(meows)
    }
}
