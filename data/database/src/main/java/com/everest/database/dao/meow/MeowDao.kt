package com.everest.database.dao.meow

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.everest.database.entity.breed.BreedEntity
import com.everest.database.entity.meow.MeowEntity
import com.everest.database.map.BreedWithMeows
import com.everest.database.map.MeowWithBreeds
import kotlinx.coroutines.flow.Flow

@Dao
interface MeowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeow(meow: MeowEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeows(meows: List<MeowEntity>)

    @Query("SELECT * FROM ${MeowEntity.TABLE_NAME}")
    suspend fun getMeows(): List<MeowEntity>

    @Query("SELECT * FROM ${MeowEntity.TABLE_NAME} where id = :id")
    suspend fun getMeowById(id:String): Flow<MeowWithBreeds>

    @Query("SELECT * FROM ${MeowEntity.TABLE_NAME} WHERE should_show = :isForPaging")
    fun pagingSource(isForPaging: Boolean): PagingSource<Int, MeowEntity>

    @Query("DELETE  FROM ${MeowEntity.TABLE_NAME} WHERE should_show = :isForPaging")
    suspend fun deleteAllPageable(isForPaging: Boolean)

    @Query("DELETE  FROM ${MeowEntity.TABLE_NAME}")
    suspend fun deleteAll()

    @Delete(entity = MeowEntity::class)
    suspend fun delete(meow: MeowEntity)

    @Transaction
    @Query("SELECT * FROM ${BreedEntity.TABLE_NAME} WHERE ${BreedEntity.BREED_ID} = :breedId")
    fun getBreedWithMeows(breedId: String): BreedWithMeows
}
