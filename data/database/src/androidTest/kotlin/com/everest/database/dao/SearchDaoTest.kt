package com.everest.database.dao

import androidx.test.filters.SmallTest
import app.cash.turbine.test
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
    @DisplayName("Successfully Insert New Search Entity")
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
    @DisplayName("Successfully delete Search Entity")
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

    @Test
    @DisplayName("Add new search entity always emit in flow")
    fun listen() = runTest {
        val createdAt1 = LocalDateTime(
            year = 1994,
            month = Month.JANUARY,
            dayOfMonth = 24,
            hour = 10,
            minute = 10,
            second = 10
        )

        val search1 = SearchEntity(
            query = "everest1",
            createdAt = createdAt1
        )

        val createdAt2 = LocalDateTime(
            year = 1997,
            month = Month.JANUARY,
            dayOfMonth = 15,
            hour = 10,
            minute = 10,
            second = 10
        )
        val search2 = SearchEntity(
            query = "everest2",
            createdAt = createdAt2
        )

        dao.insertSearch(search1)
        dao.listenSearchHistories().test {
            val expected = awaitItem()
            assertThat(expected).contains(search1)
        }

        dao.insertSearch(search2)
        dao.listenSearchHistories().test {
            val expected = awaitItem()
            assertThat(expected).contains(search1)
            assertThat(expected).contains(search2)
            assertThat(expected.size).isEqualTo(2)
        }
    }
}
