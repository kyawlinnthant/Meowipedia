package com.everest.database.entity.search

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.everest.database.entity.search.SearchEntity.Companion.TABLE_NAME
import kotlin.random.Random
import kotlinx.datetime.LocalDateTime

@Entity(
    tableName = TABLE_NAME
)
data class SearchEntity(
    val query: String,
    @ColumnInfo(name = "created_at")
    val createdAt: LocalDateTime
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = Random.nextInt()
    companion object {
        const val TABLE_NAME = "search_table"
    }
}
