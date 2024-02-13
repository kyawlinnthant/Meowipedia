package com.everest.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.everest.database.entity.MeowEntity
import com.everest.database.entity.SearchEntity

@Dao
interface MeowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeow(survey: MeowEntity)

    @Query("SELECT * FROM ${MeowEntity.TABLE_NAME} ORDER BY createdAt  DESC")
    fun getMeowList(): List<SearchEntity>
}
