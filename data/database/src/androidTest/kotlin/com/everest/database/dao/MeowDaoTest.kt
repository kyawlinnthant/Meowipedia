package com.everest.database.dao

import androidx.test.filters.SmallTest
import com.everest.database.db.MeowDatabase
import com.everest.database.entity.MeowEntity
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
class MeowDaoTest {

    @get:Rule
    val testRule = HiltAndroidRule(this)

    private lateinit var dao: MeowDao

    @Inject
    lateinit var db: MeowDatabase

    @Before
    fun setup() {
        testRule.inject()
        dao = db.provideMeowDao()
    }

    @After
    fun teardown() {
        db.clearAllTables()
        db.close()
    }

    @Test
    fun successfully_insert_individual_search() = runTest {
        val search = MeowEntity(
            name = "Rio",
            createdAt = "${Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())}",
            activeAt = "${ Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())}",
            description = "teting",
            image = "Testing URL",
            id = "ass"
        )
        dao.insertMeow(search)
        val actual = dao.getMeowList()
        Truth.assertThat(actual.size).isEqualTo(1)
    }
}
