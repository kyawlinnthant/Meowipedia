package com.everest.data.model.breed

import com.everest.database.entity.breed.BehaviorEntity
import com.everest.database.entity.breed.BreedEntity
import com.everest.database.entity.breed.BreedKeyEntity
import com.everest.database.entity.breed.CountryCodeEntity
import com.everest.database.entity.breed.FriendlyEntity
import com.everest.database.entity.breed.LevelEntity
import com.everest.database.entity.breed.WebsiteEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BreedDTO(
    val id: String,
    val name: String,
    val origin: String,
    val temperament: String,
    val description: String,
    @SerialName("life_span")
    val lifeSpan: String,
    @SerialName("alt_names")
    val nickname: String = "",
    @SerialName("reference_image_id")
    val referenceImageId: String = "",
    val weight: WeightDTO,

    // website
    @SerialName("cfa_url")
    val website1: String = "",
    @SerialName("vetstreet_url")
    val website2: String = "",
    @SerialName("vcahospitals_url")
    val website3: String = "",
    @SerialName("wikipedia_url")
    val wikipediaUrl: String = "",

    // from
    @SerialName("country_code")
    val countryCode: String,
    @SerialName("country_codes")
    val countryCodes: String,

    // friendly
    @SerialName("child_friendly")
    val childFriendly: Int,
    @SerialName("dog_friendly")
    val dogFriendly: Int,
    @SerialName("stranger_friendly")
    val strangerFriendly: Int,

    // level
    @SerialName("affection_level")
    val affectionLevel: Int,
    @SerialName("energy_level")
    val energyLevel: Int,
    @SerialName("shedding_level")
    val sheddingLevel: Int,

    // behavior
    val adaptability: Int,
    val experimental: Int,
    val grooming: Int,
    val hairless: Int,
    @SerialName("health_issues")
    val healthIssues: Int,
    val hypoallergenic: Int,
    val indoor: Int,
    val intelligence: Int,
    val lap: Int = 0,
    val natural: Int,
    val rare: Int,
    @SerialName("short_legs")
    val shortLegs: Int,
    @SerialName("social_needs")
    val socialNeeds: Int,
    @SerialName("suppressed_tail")
    val suppressedTail: Int,
    val vocalisation: Int,
    val rex: Int
) {
    fun toEntity(isForPaging: Boolean = false) = BreedEntity(
        isForPaging = isForPaging,
        id = id,
        name = name,
        origin = origin,
        temperament = temperament,
        description = description,
        lifeSpan = lifeSpan,
        nickname = nickname,
        referenceImageId = referenceImageId,
        weight = weight.toEntity(),
        website = WebsiteEntity(
            website1 = website1,
            website2 = website2,
            website3 = website3,
            wikipediaUrl = wikipediaUrl
        ),
        countryCode = CountryCodeEntity(
            countryCode = countryCode,
            countryCodes = countryCodes
        ),
        friendly = FriendlyEntity(
            childFriendly = childFriendly,
            dogFriendly = dogFriendly,
            strangerFriendly = strangerFriendly
        ),
        level = LevelEntity(
            affectionLevel = affectionLevel,
            energyLevel = energyLevel,
            sheddingLevel = sheddingLevel
        ),
        behavior = BehaviorEntity(
            adaptability = adaptability,
            experimental = experimental,
            grooming = grooming,
            hairless = hairless,
            healthIssues = healthIssues,
            hypoallergenic = hypoallergenic,
            indoor = indoor,
            intelligence = intelligence,
            lap = lap,
            natural = natural,
            rare = rare,
            shortLegs = shortLegs,
            socialNeeds = socialNeeds,
            suppressedTail = suppressedTail,
            vocalisation = vocalisation,
            rex = rex

        )
    )

    fun toKey(
        next: Int?,
        prev: Int?,
        current: Int
    ) = BreedKeyEntity(
        id = id,
        nextPage = next,
        prevPage = prev,
        currentPage = current
    )
}
