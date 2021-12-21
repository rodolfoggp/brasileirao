package com.brasileirao.data.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.LocalDateTime

@Entity(tableName = "games")
data class GameEntity(
    @Embedded(prefix = "team1Performance") val team1Performance: TeamPerformanceEntity,
    @Embedded(prefix = "team2Performance") val team2Performance: TeamPerformanceEntity,
    val dateTime: LocalDateTime,
    val location: String,
    @PrimaryKey val id: Long = 0,
)
