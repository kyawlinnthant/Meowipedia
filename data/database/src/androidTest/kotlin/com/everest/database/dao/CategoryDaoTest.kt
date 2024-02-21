package com.everest.database.dao

import androidx.test.filters.SmallTest
import com.everest.database.db.MeowDatabase
import com.everest.database.entity.CategoryEntity
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
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
class CategoryDaoTest {

    @get:Rule
    val testRule = HiltAndroidRule(this)

    private lateinit var dao: CategoryDao

    @Inject
    lateinit var db: MeowDatabase

    @Before
    fun setup() {
        testRule.inject()
        dao = db.categoryDao()
    }

    @After
    fun teardown() {
        db.clearAllTables()
        db.close()
    }

    @Test
    fun successfully_insert_individual_search() = runTest {
        val search = CategoryEntity(
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
