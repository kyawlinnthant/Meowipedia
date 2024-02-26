package com.everest.database

import android.content.Context
import androidx.room.Room
import com.everest.database.db.MeowDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): MeowDatabase = Room.databaseBuilder(
        context,
        MeowDatabase::class.java,
        MeowDatabase.DB_NAME
    )
        .fallbackToDestructiveMigration()
        .build()
}
