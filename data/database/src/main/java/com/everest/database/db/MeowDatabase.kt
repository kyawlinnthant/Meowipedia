package com.everest.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.everest.database.dao.CategoryDao
import com.everest.database.dao.MeowDao
import com.everest.database.dao.MeowKeyDao
import com.everest.database.dao.SearchDao
import com.everest.database.entity.CategoryEntity
import com.everest.database.entity.MeowEntity
import com.everest.database.entity.MeowKeyEntity
import com.everest.database.entity.SearchEntity
import com.everest.database.typeconverter.LocalDateTimeConverter

@Database(
    entities = [
        CategoryEntity::class,
        SearchEntity::class,
        MeowEntity::class,
        MeowKeyEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(value = [LocalDateTimeConverter::class])
abstract class MeowDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun searchDao(): SearchDao

    abstract fun meowDao(): MeowDao
    abstract fun meowKeyDao(): MeowKeyDao

    companion object {
        const val DB_NAME = "meow_database"
    }
}
