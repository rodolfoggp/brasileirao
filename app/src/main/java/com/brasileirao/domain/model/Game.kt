package com.brasileirao.domain.model

import org.threeten.bp.LocalDateTime

data class Game(
    val id: Long,
    val team1Performance: TeamPerformance,
    val team2Performance: TeamPerformance,
    val dateTime: LocalDateTime,
    val location: String,
    val highlights: List<Highlight>,
)
