package com.everest.dao

import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.isEqualTo
import com.everest.database.dao.breed.BreedDao
import com.everest.database.dao.meow.MeowDao
import com.everest.database.dao.meow.MeowKeyDao
import com.everest.database.db.MeowDatabase
import com.everest.database.entity.breed.BehaviorEntity
import com.everest.database.entity.breed.BreedEntity
import com.everest.database.entity.breed.CountryCodeEntity
import com.everest.database.entity.breed.FriendlyEntity
import com.everest.database.entity.breed.LevelEntity
import com.everest.database.entity.breed.WebsiteEntity
import com.everest.database.entity.breed.WeightEntity
import com.everest.database.entity.meow.MeowEntity
import com.everest.database.entity.meow.MeowKeyEntity
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Test


class MeowDaoTest {

    private val meowDao: MeowDao = mockk(relaxed = true)
    private val meowKeyDao: MeowKeyDao = mockk(relaxed = true)
    private val breedDao: BreedDao = mockk(relaxed = true)

    private val db: MeowDatabase = mockk(relaxed = true)

    @After
    fun teardown() {
        db.clearAllTables()
        db.close()
    }


    @Test
//    @DisplayName("Successfully Insert New Entity")
    fun insert() = runTest {
        val meowKeyEntity = listOf(
            MeowKeyEntity(
                id = "1",
                currentPage = 1,
                nextPage = 2,
                prevPage = 0
            )
        )

        val meowEntity = listOf(
            MeowEntity(
                isForPaging = true,
                width = 150,
                height = 150,
                id = "111",
                url = "www.google.com",
                breedId = "fff"
            ),
            MeowEntity(
                isForPaging = true,
                width = 150,
                height = 150,
                id = "222",
                url = "www.google.com",
                breedId = "ggg"
            )

        )

        val breedEntity = listOf(
            BreedEntity(
                isForPaging = true,
                id = "fff",
                name = "Breed 1",
                behavior = BehaviorEntity(
                    adaptability = 10,
                    experimental = 10,
                    grooming = 1,
                    socialNeeds = 1,
                    intelligence = 1,
                    hairless = 1,
                    healthIssues = 1,
                    hypoallergenic = 1,
                    indoor = 2,
                    lap = 3,
                    natural = 1,
                    rare = 1,
                    rex = 2,
                    shortLegs = 3,
                    suppressedTail = 3,
                    vocalisation = 1
                ),
                countryCode = CountryCodeEntity(
                    countryCode = "MM",
                    countryCodes = "MM,ARG"
                ),
                description = "Wow",
                lifeSpan = "10 Years",
                nickname = "Hehe",
                origin = "WOooooo",
                website = WebsiteEntity(
                    website1 = "",
                    website2 = "",
                    website3 = "",
                    wikipediaUrl = ""
                ),
                friendly = FriendlyEntity(
                    childFriendly = 10,
                    dogFriendly = 2,
                    strangerFriendly = 0,
                ),
                referenceImageId = "wrwrw",
                temperament = "TEMPE",
                weight = WeightEntity(
                    imperial = "12131",
                    metric = "erwrwr"
                ),
                level = LevelEntity(
                    affectionLevel = 1,
                    energyLevel = 10,
                    sheddingLevel = 2
                )
            ),


            )
        breedDao.insertBreeds(breedEntity)
        meowDao.insertMeows(meowEntity)
        meowKeyDao.insertKeys(meowKeyEntity)

        val actual = meowDao.getMeows()
        assertThat(actual.size).isEqualTo(1)
        assertThat(actual).contains(meowEntity)
    }
}
