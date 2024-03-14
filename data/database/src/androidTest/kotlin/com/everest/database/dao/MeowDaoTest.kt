package com.everest.database.dao


import androidx.test.filters.SmallTest
import assertk.assertThat
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
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@SmallTest
@HiltAndroidTest
class MeowDaoTest {

    @get:Rule
    val testRule = HiltAndroidRule(this)

    private lateinit var breedDao: BreedDao
    private lateinit var meowDao: MeowDao
    private lateinit var meowKeyDao: MeowKeyDao

    @Inject
    lateinit var db: MeowDatabase

    @BeforeEach
    fun setup() {
        testRule.inject()
        breedDao = db.breedDao()
        meowDao = db.meowDao()
        meowKeyDao = db.meowKeyDao()
    }

    @AfterEach
    fun teardown() {
        db.clearAllTables()
        db.close()
    }


    @Test
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
        assertThat(actual).isEqualTo(meowEntity)
    }

    @Test
    fun delete() = runTest {
        meowDao.deleteAllPageable(isForPaging = true)
        meowKeyDao.deleteAll()

        val actual = meowDao.getMeows()
        assertThat(actual.size).isEqualTo(0)
    }
}
