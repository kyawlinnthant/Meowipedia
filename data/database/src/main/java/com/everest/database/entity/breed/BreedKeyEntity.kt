package com.everest.database.entity.breed

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.everest.database.entity.breed.BreedKeyEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class BreedKeyEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val nextPage: Int?,
    val prevPage: Int?,
    val currentPage: Int
) {
    companion object {
        const val TABLE_NAME = "breed_key"
    }
}
