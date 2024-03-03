package com.everest.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.everest.testrule.CoroutinesTestRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.After
import org.junit.Before
import org.junit.Rule
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


}
