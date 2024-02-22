package com.everest.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.everest.database.entity.CategoryKeyEntity

@Dao
interface CategoryKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCategoryKey(key: CategoryKeyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMeowKeys(keys: List<CategoryKeyEntity>)

    @Query("SELECT * FROM ${CategoryKeyEntity.TABLE_NAME} WHERE categoryId =:id")
    suspend fun getCategoryKey(id: String): CategoryKeyEntity

    @Query("DELETE FROM ${CategoryKeyEntity.TABLE_NAME}")
    suspend fun deleteAll()

    @Delete(entity = CategoryKeyEntity::class)
    suspend fun deleteCategoryKey(key: CategoryKeyEntity)
}
