package com.everest.domain.model.categories.breed

data class BreedVo(
    val id: String,
    val name: String,
    val origin: String,
    val temperament: String,
    val description: String,
    val lifeSpan: String,
    val nickname: String,
    val referenceImageId: String,

    val weight: WeightVo,

    val website: WebsiteVo,

    val countryCode: CountryCodeVo,

    val friendly: FriendlyVo,

    val level: LevelVo,

    val behavior: BehaviorVo

)

data class WeightVo(
    val imperial: String,
    val metric: String
)

data class WebsiteVo(
    val website1: String,
    val website2: String,
    val website3: String,
    val wikipediaUrl: String
)

data class CountryCodeVo(
    val countryCode: String,
    val countryCodes: String
)

data class FriendlyVo(
    val childFriendly: Int,
    val dogFriendly: Int,
    val strangerFriendly: Int

)

data class LevelVo(
    val affectionLevel: Int,
    val energyLevel: Int,
    val sheddingLevel: Int
)

data class BehaviorVo(
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
