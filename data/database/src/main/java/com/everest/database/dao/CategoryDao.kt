package com.everest.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.everest.database.entity.CategoryEntity

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(meows: List<CategoryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(survey: CategoryEntity)

    @Query("SELECT * FROM ${CategoryEntity.TABLE_NAME}")
    fun pagingSource(): PagingSource<Int, CategoryEntity>

    @Query("SELECT * FROM ${CategoryEntity.TABLE_NAME} ORDER BY createdAt  DESC")
    fun getMeowList(): List<CategoryEntity>
}
