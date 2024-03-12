package com.everest.database.dao.breed

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.everest.database.entity.breed.BreedEntity

@Dao
interface BreedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBreeds(breeds: List<BreedEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBreed(breed: BreedEntity)

    @Query("SELECT * FROM ${BreedEntity.TABLE_NAME} WHERE should_show = :isForPaging")
    fun pagingSource(isForPaging: Boolean): PagingSource<Int, BreedEntity>

//    @Query("SELECT * FROM ${BreedEntity.TABLE_NAME}")
//    suspend fun getBreeds(): List<BreedEntity>

    @Query("DELETE  FROM ${BreedEntity.TABLE_NAME} WHERE should_show = :isForPaging")
    suspend fun deleteAllPageable(isForPaging: Boolean)

    @Query("DELETE  FROM ${BreedEntity.TABLE_NAME}")
    suspend fun deleteAll()

    @Delete(entity = BreedEntity::class)
    suspend fun delete(meow: BreedEntity)
}
