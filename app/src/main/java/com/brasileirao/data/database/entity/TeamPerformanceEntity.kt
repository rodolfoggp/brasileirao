package com.brasileirao.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TeamPerformanceEntity(
    val team: String,
    val score: Int,
    val badge: String,
    @PrimaryKey(autoGenerate = true) var id: Long = 0L,
)