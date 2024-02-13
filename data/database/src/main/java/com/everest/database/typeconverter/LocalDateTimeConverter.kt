package com.everest.database.typeconverter

import androidx.room.TypeConverter
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

class LocalDateTimeConverter {

    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDateTime? {
        return value?.let { milliSecond ->
            Instant.fromEpochMilliseconds(milliSecond)
                .toLocalDateTime(TimeZone.currentSystemDefault())
        }
    }

    @TypeConverter
    fun toTimestamp(dateTime: LocalDateTime?): Long? {
        return dateTime?.toInstant(TimeZone.currentSystemDefault())?.toEpochMilliseconds()
    }
}
