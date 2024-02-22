package com.everest.categories.data.model.categories

import com.everest.categories.data.model.Weight
import com.everest.categories.data.model.search.CategoryImageDTO
import com.everest.database.entity.CategoryEntity
import com.everest.database.entity.CategoryKeyEntity
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryDTO(
    val adaptability: Int? = -1,
    @SerialName("affection_level")
    val affectionLevel: Int? = -1,
    @SerialName("alt_names")
    val altNames: String? = "",
//    @SerialName("cfa_url")
//    val cfaUrl: String?,
    @SerialName("child_friendly")
    val childFriendly: Int? = -1,
    @SerialName("country_code")
    val countryCode: String? = "",
    @SerialName("country_codes")
    val countryCodes: String? = "",
    val description: String? = "",
    @SerialName("dog_friendly")
    val dogFriendly: Int? = -1,
    @SerialName("energy_level")
    val energyLevel: Int? = -1,
    val experimental: Int? = -1,
    val grooming: Int? = -1,
    val hairless: Int? = -1,
    @SerialName("health_issues")
    val healthIssues: Int? = -1,
    val hypoallergenic: Int? = -1,
    val id: String? = "",
    val image: CategoryImageDTO? = null,
    val indoor: Int? = -1,
    val intelligence: Int? = -1,
//    val lap: Int? = -1,
    @SerialName("life_span")
    val lifeSpan: String? = "",
    val name: String? = "",
    val natural: Int? = -1,
    val origin: String? = "",
    val rare: Int? = -1,
    @SerialName("reference_image_id")
    val referenceImgId: String? = "",
    val rex: Int? = -1,
    @SerialName("shedding_level")
    val sheddingLevel: Int? = -1,
    @SerialName("short_legs")
    val shortLegs: Int? = -1,
    @SerialName("social_needs")
    val socialNeeds: Int? = -1,
    @SerialName("stranger_friendly")
    val strangerFriendly: Int? = -1,
    @SerialName("suppressed_tail")
    val suppressedTail: Int? = -1,
    val temperament: String? = "",
//    @SerialName("vcahospitals_url")
//    val vcahoSpitalsUrl: String? = "",
//    @SerialName("vetstreet_url")
//    val vetStreetUrl: String? = "",
    val vocalisation: Int? = -1,
    val weight: Weight? = null
//    @SerialName("wikipedia_url")
//    val wikipediaUrl: String? = ""
) {

    fun toCategoryEntity() = CategoryEntity(
        id = id ?: "",
        name = name ?: "",
        description = description ?: "",
        image = image?.url ?: "",
        createdAt = "${Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())}",
        activeAt = "${Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())}"
    )

    fun toKeyEntity(
        next: Int?,
        prev: Int?,
        current: Int
    ) = CategoryKeyEntity(
        categoryId = id ?: "",
        nextPage = next,
        prevPage = prev,
        currentPage = current
    )
}
