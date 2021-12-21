package com.brasileirao.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "highlights")
data class HighlightEntity(
    val minute: Int,
    val description: String,
    var gameId: Long,
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
)