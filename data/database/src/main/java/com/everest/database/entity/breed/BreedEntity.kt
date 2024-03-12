package com.everest.database.entity.breed

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.everest.database.entity.breed.BreedEntity.Companion.BREED_ID

@Entity(
    tableName = BreedEntity.TABLE_NAME,
    indices = [
        Index(
            value = [BREED_ID],
            unique = true
        )
    ]
)
data class BreedEntity(

    @ColumnInfo("should_show")
    val isForPaging: Boolean = false,

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = BREED_ID)
    val id: String,
    val name: String,
    val origin: String,
    val temperament: String,
    val description: String,
    val lifeSpan: String,
    val nickname: String,
    val referenceImageId: String,
    @Embedded
    val weight: WeightEntity,
    @Embedded
    val website: WebsiteEntity,
    @Embedded
    val countryCode: CountryCodeEntity,
    @Embedded
    val friendly: FriendlyEntity,
    @Embedded
    val level: LevelEntity,
    @Embedded
    val behavior: BehaviorEntity

) {
    companion object {
        const val TABLE_NAME = "breed_table"
        const val BREED_ID = "breed_id"
    }
}

data class WeightEntity(
    val imperial: String,
    val metric: String
)

data class WebsiteEntity(
    val website1: String,
    val website2: String,
    val website3: String,
    val wikipediaUrl: String
)

data class CountryCodeEntity(
    val countryCode: String,
    val countryCodes: String
)

data class FriendlyEntity(
    val childFriendly: Int,
    val dogFriendly: Int,
    val strangerFriendly: Int

)

data class LevelEntity(
    val affectionLevel: Int,
    val energyLevel: Int,
    val sheddingLevel: Int
)

data class BehaviorEntity(
    val adaptability: Int,
    val experimental: Int,
    val grooming: Int,
    val hairless: Int,
    val healthIssues: Int,
    val hypoallergenic: Int,
    val indoor: Int,
    val intelligence: Int,
    val lap: Int,
    val natural: Int,
    val rare: Int,
    val shortLegs: Int,
    val socialNeeds: Int,
    val suppressedTail: Int,
    val vocalisation: Int,
    val rex: Int
)
