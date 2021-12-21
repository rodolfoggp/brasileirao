package com.brasileirao.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.brasileirao.data.database.dao.GameDao
import com.brasileirao.data.database.entity.GameEntity
import com.brasileirao.data.database.entity.HighlightEntity

@Database(entities = [GameEntity::class, HighlightEntity::class], version = 1)
@TypeConverters(LocalDateTimeConverter::class)
abstract class GamesDatabase : RoomDatabase() {
    abstract fun getGameDao(): GameDao
}