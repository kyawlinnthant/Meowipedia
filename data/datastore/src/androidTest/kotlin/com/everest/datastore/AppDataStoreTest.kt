package com.everest.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.everest.testrule.CoroutinesTestRule
import com.everest.type.ThemeType
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
    val coroutinesRule = CoroutinesTestRule()

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
            io = coroutinesRule.testDispatcher
        )
    }

    @After
    fun teardown() {
        pref = null
    }

    @Test
    fun setDayTheme() = runTest {
        pref?.let {
            it.putTheme(ThemeType.DayType)

            it.pullTheme().test {
                assertThat(awaitItem()).isEqualTo(ThemeType.DayType)
            }
        }
    }

    @Test
    fun setNightTheme() = runTest {
        pref?.let {
            it.putTheme(ThemeType.NightType)

            it.pullTheme().test {
                assertThat(awaitItem()).isEqualTo(ThemeType.NightType)
            }
        }
    }

    @Test
    fun enableDynamicTheme() = runTest {
        pref?.let {
            it.putEnabledDynamic(true)

            it.pullEnabledDynamic().test {
                assertThat(awaitItem()).isEqualTo(true)
            }
        }
    }

    @Test
    fun disableDynamicTheme() = runTest {
        pref?.let {
            it.putEnabledDynamic(false)
            it.pullEnabledDynamic().test {
                assertThat(awaitItem()).isEqualTo(false)
            }
        }
    }

}
