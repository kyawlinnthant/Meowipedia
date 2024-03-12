package com.everest.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.everest.database.dao.breed.BreedDao
import com.everest.database.dao.breed.BreedKeyDao
import com.everest.database.dao.meow.MeowDao
import com.everest.database.dao.meow.MeowKeyDao
import com.everest.database.dao.search.SearchDao
import com.everest.database.entity.breed.BreedEntity
import com.everest.database.entity.breed.BreedKeyEntity
import com.everest.database.entity.meow.MeowEntity
import com.everest.database.entity.meow.MeowKeyEntity
import com.everest.database.entity.search.SearchEntity
import com.everest.database.typeconverter.LocalDateTimeConverter

@Database(
    entities = [
        SearchEntity::class,
        MeowEntity::class,
        MeowKeyEntity::class,
        BreedEntity::class,
        BreedKeyEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(value = [LocalDateTimeConverter::class])
abstract class MeowDatabase : RoomDatabase() {
    abstract fun searchDao(): SearchDao

    abstract fun meowDao(): MeowDao
    abstract fun meowKeyDao(): MeowKeyDao
    abstract fun breedDao(): BreedDao
    abstract fun breedKeyDao(): BreedKeyDao

    companion object {
        const val DB_NAME = "meow_database"
    }
}
