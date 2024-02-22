package com.everest.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.everest.database.entity.MeowEntity

@Dao
interface MeowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeow(meow: MeowEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeows(meows: List<MeowEntity>)

    @Query("SELECT * FROM ${MeowEntity.TABLE_NAME}")
    suspend fun getMeows(): List<MeowEntity>

    @Query("SELECT * FROM ${MeowEntity.TABLE_NAME}")
    fun pagingSource(): PagingSource<Int, MeowEntity>

    @Query("DELETE FROM ${MeowEntity.TABLE_NAME}")
    suspend fun deleteAll()

    @Delete(entity = MeowEntity::class)
    suspend fun deleteMeow(meow: MeowEntity)
}
