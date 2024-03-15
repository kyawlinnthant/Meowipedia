package com.everest.domain.model.categories.breed

import com.everest.data.model.breed.BreedDTO
import com.everest.data.model.breed.WeightDTO
import com.everest.database.entity.breed.BehaviorEntity
import com.everest.database.entity.breed.BreedEntity
import com.everest.database.entity.breed.CountryCodeEntity
import com.everest.database.entity.breed.FriendlyEntity
import com.everest.database.entity.breed.LevelEntity
import com.everest.database.entity.breed.WebsiteEntity
import com.everest.database.entity.breed.WeightEntity

fun BreedEntity.toVo() = BreedVo(
    id = id,
    name = name,
    origin = origin,
    temperament = temperament,
    description = description,
    lifeSpan = lifeSpan,
    nickname = nickname,
    referenceImageId = referenceImageId,
    weight = weight.toVo(),
    website = website.toVo(),
    countryCode = countryCode.toVo(),
    friendly = friendly.toVo(),
    level = level.toVo(),
    behavior = behavior.toVo()
)

fun WeightEntity.toVo() = WeightVo(
    imperial = imperial,
    metric = metric
)

fun WebsiteEntity.toVo() = WebsiteVo(
    website1 = website1,
    website2 = website2,
    website3 = website3,
    wikipediaUrl = wikipediaUrl
)

fun CountryCodeEntity.toVo() = CountryCodeVo(
    countryCode = countryCode,
    countryCodes = countryCodes
)

fun FriendlyEntity.toVo() = FriendlyVo(
    childFriendly = childFriendly,
    dogFriendly = dogFriendly,
    strangerFriendly = strangerFriendly
)

fun LevelEntity.toVo() = LevelVo(
    affectionLevel = affectionLevel,
    energyLevel = energyLevel,
    sheddingLevel = sheddingLevel
)

fun BehaviorEntity.toVo() = BehaviorVo(
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

// dto
fun BreedDTO.toVo() = BreedVo(
    id = id,
    name = name,
    origin = origin,
    temperament = temperament,
    description = description,
    lifeSpan = lifeSpan,
    nickname = nickname,
    referenceImageId = referenceImageId,
    weight = weight.toVo(),
    website = WebsiteVo(
        website1 = website1,
        website2 = website2,
        website3 = website3,
        wikipediaUrl = wikipediaUrl
    ),
    countryCode = CountryCodeVo(
        countryCode = countryCode,
        countryCodes = countryCodes
    ),
    friendly = FriendlyVo(
        childFriendly = childFriendly,
        dogFriendly = dogFriendly,
        strangerFriendly = strangerFriendly
    ),
    level = LevelVo(
        affectionLevel = affectionLevel,
        energyLevel = energyLevel,
        sheddingLevel = sheddingLevel
    ),
    behavior = BehaviorVo(
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

fun WeightDTO.toVo() = WeightVo(
    imperial = imperial,
    metric = metric
)
