package com.everest.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.everest.database.entity.MeowKeyEntity

@Dao
interface MeowKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMeowKey(key: MeowKeyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMeowKeys(keys: List<MeowKeyEntity>)

    @Query("SELECT * FROM ${MeowKeyEntity.TABLE_NAME} WHERE id =:id")
    suspend fun getMeowKey(id: String): MeowKeyEntity

    @Query("DELETE FROM ${MeowKeyEntity.TABLE_NAME}")
    suspend fun deleteAll()

    @Delete(entity = MeowKeyEntity::class)
    suspend fun deleteMeowKey(key: MeowKeyEntity)
}
