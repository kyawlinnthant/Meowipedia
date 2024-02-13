package com.everest.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.everest.database.entity.SearchEntity.Companion.TABLE_NAME
import kotlinx.datetime.LocalDateTime
import kotlin.random.Random

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
