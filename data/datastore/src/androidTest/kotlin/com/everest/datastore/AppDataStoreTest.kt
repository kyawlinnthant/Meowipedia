package com.everest.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.everest.testrule.CoroutinesTestDispatcherJunit4
import com.everest.type.DayNightTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@UninstallModules(DataStoreModule::class)
class AppDataStoreTest {

    @get:Rule
    val testRule = CoroutinesTestDispatcherJunit4()

    /**
     * ### Inject Hilt with JUnit5 has some bugs
     * https://stackoverflow.com/questions/67877721/nullpointerexception-while-trying-hilt-injections-in-junit5-jupiter-test-cases
     */
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private var pref: AppDataStoreImpl? = null

    @Inject
    lateinit var ds: DataStore<Preferences>

    @Before
    fun setup() {
        hiltRule.inject()
        pref = AppDataStoreImpl(
            ds = ds,
            io = testRule.testDispatcher
        )
    }

    @After
    fun teardown() {
        pref = null
    }

    @Test
    fun save_Theme() = runTest {
        val expected = DayNightTheme.System
        pref!!.putTheme(theme = expected)
        pref!!.pullTheme().test {
            val output = awaitItem()
            assertThat(output).isEqualTo(expected)
        }
    }

    @Test
    fun save_Dynamic() = runTest {
        val expected = true
        pref!!.putEnabledDynamic(enabled = expected)
        pref!!.pullEnabledDynamic().test {
            val output = awaitItem()
            assertThat(output).isEqualTo(expected)
        }
    }
}
