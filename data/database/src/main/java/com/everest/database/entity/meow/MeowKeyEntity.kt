package com.everest.database.entity.meow

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.everest.database.entity.meow.MeowKeyEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class MeowKeyEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val nextPage: Int?,
    val prevPage: Int?,
    val currentPage: Int
) {
    companion object {
        const val TABLE_NAME = "meow_key"
    }
}
