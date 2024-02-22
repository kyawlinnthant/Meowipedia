package com.everest.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.everest.database.entity.MeowEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class MeowEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val height: Int,
    val width: Int,
    val url: String
) {
    companion object {
        const val TABLE_NAME = "meow_table"
    }
}
