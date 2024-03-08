package com.everest.database.dao

import androidx.test.filters.SmallTest
import assertk.assertThat
import assertk.assertions.contains
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import com.everest.database.dao.search.SearchDao
import com.everest.database.db.MeowDatabase
import com.everest.database.entity.search.SearchEntity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.Month
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import javax.inject.Inject

@SmallTest
@HiltAndroidTest
class SearchDaoTest {

    @get:Rule
    val testRule = HiltAndroidRule(this)

    private lateinit var dao: SearchDao

    @Inject
    lateinit var db: MeowDatabase

    @Before
    fun setup() {
        testRule.inject()
        dao = db.searchDao()
    }

    @After
    fun teardown() {
        db.clearAllTables()
        db.close()
    }


    @Test
    @DisplayName("Successfully Insert New Entity")
    fun insert() = runTest {

        val createdAt = LocalDateTime(
            year = 1994,
            month = Month.JANUARY,
            dayOfMonth = 24,
            hour = 10,
            minute = 10,
            second = 10
        )

        val search = SearchEntity(
            query = "everest",
            createdAt = createdAt
        )

        dao.insertSearch(search)

        val actual = dao.getSearchHistories()
        assertThat(actual.size).isEqualTo(1)
        assertThat(actual).contains(search)

    }

    @Test
    @DisplayName("Successfully delete expected Entity")
    fun delete() = runTest {
        val createdAt = LocalDateTime(
            year = 1994,
            month = Month.JANUARY,
            dayOfMonth = 24,
            hour = 10,
            minute = 10,
            second = 10
        )

        val search = SearchEntity(
            query = "everest",
            createdAt = createdAt
        )

        dao.insertSearch(search)
        dao.deleteSearch(query = "everest")
        val actual = dao.getSearchHistories()
        assertThat(actual).isEmpty()
    }
}
