package com.brasileirao.data.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class GameWithHighlights(
    @Embedded val game: GameEntity,
    @Relation(parentColumn = "id", entityColumn = "gameId")
    val highlights: List<HighlightEntity>
)