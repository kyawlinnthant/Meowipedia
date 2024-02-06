package com.everest.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.everest.database.entity.MeowEntity

@Dao
interface MeowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeow(survey: MeowEntity)
}