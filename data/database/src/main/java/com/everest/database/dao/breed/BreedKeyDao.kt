package com.everest.database.dao.breed

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.everest.database.entity.breed.BreedKeyEntity

@Dao
interface BreedKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKey(key: BreedKeyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKeys(keys: List<BreedKeyEntity>)

    @Query("SELECT * FROM ${BreedKeyEntity.TABLE_NAME} WHERE id =:breedId")
    suspend fun getKeyById(breedId: String): BreedKeyEntity

    @Query("DELETE FROM ${BreedKeyEntity.TABLE_NAME}")
    suspend fun deleteAll()

    @Delete(entity = BreedKeyEntity::class)
    suspend fun delete(key: BreedKeyEntity)
}
