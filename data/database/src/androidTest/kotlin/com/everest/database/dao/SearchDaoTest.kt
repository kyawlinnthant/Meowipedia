package com.everest.database.dao

import androidx.test.filters.SmallTest
import com.everest.database.db.MeowDatabase
import com.everest.database.entity.SearchEntity
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
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
        dao = db.provideSearchDao()
    }

    @After
    fun teardown() {
        db.clearAllTables()
        db.close()
    }

    @Test
    fun successfully_insert_individual_search() = runTest {
        val search = SearchEntity(
            query = "kyawlinnthant",
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        )
        dao.insertSearch(search)
        val actual = dao.getSearchHistories()
        Truth.assertThat(actual.size).isEqualTo(1)
    }

    @Test
    fun get_search_dao_list() = runTest {
        val search = SearchEntity(
            query = "kyawlinnthant",
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        )
        dao.insertSearch(search)
        val actual = dao.listenSearchHistories().first()
        Truth.assertThat(actual.size).isEqualTo(1)
    }
}
