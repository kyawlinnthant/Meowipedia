package com.everest.database.dao.meow

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.everest.database.entity.meow.MeowKeyEntity

@Dao
interface MeowKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKey(key: MeowKeyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKeys(keys: List<MeowKeyEntity>)

    @Query("SELECT * FROM ${MeowKeyEntity.TABLE_NAME} WHERE id =:meowId")
    suspend fun getKeyById(meowId: String): MeowKeyEntity

    @Query("DELETE FROM ${MeowKeyEntity.TABLE_NAME}")
    suspend fun deleteAll()

    @Delete(entity = MeowKeyEntity::class)
    suspend fun delete(key: MeowKeyEntity)
}
