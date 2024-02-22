package com.everest.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.everest.database.entity.CategoriesRemoteKeyEntity.Companion.TABLE_NAME


@Entity(tableName = TABLE_NAME)
data class CategoriesRemoteKeyEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val nextPage: Int?,
    val prevPage: Int?,
    val currentPage : Int,
) {
    companion object {
        const val TABLE_NAME = "categories_remote_key"
    }
}
