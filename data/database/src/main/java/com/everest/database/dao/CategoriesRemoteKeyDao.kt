package com.everest.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.everest.database.entity.CategoriesRemoteKeyEntity

@Dao
interface CategoriesRemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRemoteKey(remoteKey: CategoriesRemoteKeyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRemoteKeys(remoteKeys: List<CategoriesRemoteKeyEntity>)

    @Query("SELECT * FROM ${CategoriesRemoteKeyEntity.TABLE_NAME} WHERE id =:id")
    suspend fun getRemoteKey(id: String): CategoriesRemoteKeyEntity

    @Query("DELETE FROM ${CategoriesRemoteKeyEntity.TABLE_NAME}")
    suspend fun deleteAll()

    @Delete(entity = CategoriesRemoteKeyEntity::class)
    suspend fun deleteRemoteKey(remoteKey: CategoriesRemoteKeyEntity)
}
