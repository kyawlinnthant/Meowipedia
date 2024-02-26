package com.everest.database.dao.search

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.everest.database.entity.search.SearchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearch(search: SearchEntity)

    @Query("DELETE FROM ${SearchEntity.TABLE_NAME} WHERE `query` = :query")
    suspend fun deleteSearch(query: String)

    @Query("SELECT * FROM ${SearchEntity.TABLE_NAME} ORDER BY created_at DESC")
    fun listenSearchHistories(): Flow<List<SearchEntity>>

    @Query("SELECT * FROM ${SearchEntity.TABLE_NAME} ORDER BY created_at DESC")
    fun getSearchHistories(): List<SearchEntity>
}
