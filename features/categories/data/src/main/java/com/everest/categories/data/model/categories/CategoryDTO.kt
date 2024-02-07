package com.everest.categories.data.model.categories

import com.everest.categories.data.model.Weight
import com.everest.categories.data.model.search.CategoryImageDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryDTO(
    val adaptability: Int?,
    @SerialName("affection_level")
    val affectionLevel: Int?,
    @SerialName("alt_names")
    val altNames: String?,
    @SerialName("bidability")
    val bidAbility: Int?,
    @SerialName("cat_friendly")
    val catFriendly: Int?,
    @SerialName("cfa_url")
    val cfaUrl: String?,
    @SerialName("child_friendly")
    val childFriendly: Int?,
    @SerialName("country_code")
    val countryCode: String?,
    @SerialName("country_codes")
    val countryCodes: String?,
    val description: String?,
    @SerialName("dog_friendly")
    val dogFriendly: Int?,
    @SerialName("energy_level")
    val energyLevel: Int?,
    val experimental: Int?,
    val grooming: Int?,
    val hairless: Int?,
    @SerialName("health_issues")
    val healthIssues: Int?,
    val hypoallergenic: Int?,
    val id: String?,
    val image: CategoryImageDTO,
    val indoor: Int?,
    val intelligence: Int?,
    val lap: Int?,
    @SerialName("life_span")
    val lifeSpan: String?,
    val name: String?,
    val natural: Int?,
    val origin: String?,
    val rare: Int?,
    @SerialName("reference_image_id")
    val referenceImgId: String?,
    val rex: Int?,
    @SerialName("shedding_level")
    val sheddingLevel: Int?,
    @SerialName("short_legs")
    val shortLegs: Int?,
    @SerialName("social_needs")
    val socialNeeds: Int?,
    @SerialName("stranger_friendly")
    val strangerFriendly: Int?,
    @SerialName("suppressed_tail")
    val suppressedTail: Int?,
    val temperament: String?,
    @SerialName("vcahospitals_url")
    val vcahoSpitalsUrl: String?,
    @SerialName("vetstreet_url")
    val vetStreetUrl: String?,
    val vocalisation: Int?,
    val weight: Weight?,
    @SerialName("wikipedia_url")
    val wikipediaUrl: String?
)

