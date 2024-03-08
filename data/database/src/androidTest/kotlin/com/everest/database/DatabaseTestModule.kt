package com.everest.database

import android.content.Context
import androidx.room.Room
import com.everest.database.db.MeowDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
object DbTestModule {
    @Provides
    @Singleton
    fun provideInMemoryDb(
        @ApplicationContext context: Context
    ): MeowDatabase = Room.inMemoryDatabaseBuilder(
        context,
        MeowDatabase::class.java
    ).allowMainThreadQueries().build()
}

