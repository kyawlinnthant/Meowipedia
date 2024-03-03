package com.everest.database.entity.meow

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.everest.database.entity.breed.BreedEntity
import com.everest.database.entity.meow.MeowEntity.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = BreedEntity::class,
            parentColumns = [BreedEntity.BREED_ID],
            childColumns = [MeowEntity.BREED_ID],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class MeowEntity(

    @ColumnInfo("should_show")
    val isForPaging: Boolean = false,

    @PrimaryKey(autoGenerate = false)
    val id: String,
    val height: Int,
    val width: Int,
    val url: String,
    @ColumnInfo(name = BREED_ID)
    val breedId: String
) {
    companion object {
        const val TABLE_NAME = "meow_table"
        const val BREED_ID = "breed_id"
    }
}
