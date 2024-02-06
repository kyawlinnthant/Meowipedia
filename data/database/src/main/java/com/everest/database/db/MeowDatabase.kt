package com.everest.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.everest.database.dao.MeowDao
import com.everest.database.entity.MeowEntity

@Database(
    entities = [MeowEntity::class],
    version = 1
)
abstract class MeowDatabase : RoomDatabase() {
    abstract fun provideMeowDao(): MeowDao

    companion object {
        const val DB_NAME = "meow_database"
    }
}
