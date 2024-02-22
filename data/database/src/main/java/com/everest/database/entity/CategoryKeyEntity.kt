package com.everest.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.everest.database.entity.CategoryKeyEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class CategoryKeyEntity(
    @PrimaryKey(autoGenerate = false)
    val categoryId: String,
    val nextPage: Int?,
    val prevPage: Int?,
    val currentPage: Int
) {
    companion object {
        const val TABLE_NAME = "category_meow_key"
    }
}
