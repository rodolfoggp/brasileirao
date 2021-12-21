package com.brasileirao.data.database

import androidx.room.TypeConverter
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

class LocalDateTimeConverter {
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    @TypeConverter
    fun toTimestamp(value: LocalDateTime): String = formatter.format(value)

    @TypeConverter
    fun fromTimestamp(string: String): LocalDateTime = LocalDateTime.parse(string)
}
