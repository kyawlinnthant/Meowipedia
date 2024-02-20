package com.everest.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.everest.database.dao.MeowDao
import com.everest.database.dao.SearchDao
import com.everest.database.entity.MeowEntity
import com.everest.database.entity.SearchEntity
import com.everest.database.typeconverter.LocalDateTimeConverter
@Database(
    entities = [MeowEntity::class, SearchEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(value = [LocalDateTimeConverter::class])
abstract class MeowDatabase : RoomDatabase() {
    abstract fun provideMeowDao(): MeowDao
    abstract fun provideSearchDao(): SearchDao

    companion object {
        const val DB_NAME = "meow_database"
    }
}
