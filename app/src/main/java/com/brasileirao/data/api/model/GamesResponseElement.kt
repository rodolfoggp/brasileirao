package com.brasileirao.data.api.model

import com.brasileirao.domain.model.Highlight
import com.brasileirao.domain.model.TeamPerformance
import org.threeten.bp.LocalDateTime

data class GamesResponseElement(
    val id: Long,
    val team1Performance: TeamPerformance,
    val team2Performance: TeamPerformance,
    val dateTime: LocalDateTime,
    val location: String,
    val highlights: List<Highlight>,
)