package com.brasileirao.data.database.mapper

import com.brasileirao.data.database.entity.GameEntity
import com.brasileirao.data.database.entity.GameWithHighlights
import com.brasileirao.data.database.entity.HighlightEntity
import com.brasileirao.data.database.entity.TeamPerformanceEntity
import com.brasileirao.domain.model.Game
import com.brasileirao.domain.model.Highlight
import com.brasileirao.domain.model.TeamPerformance

fun TeamPerformance.toEntity() =
    TeamPerformanceEntity(team, score, badge)

fun TeamPerformanceEntity.toModel() =
    TeamPerformance(team, score, badge)

fun List<Highlight>.toEntity(gameId: Long) =
    map { HighlightEntity(it.minute, it.description, gameId) }

fun List<HighlightEntity>.toModel() =
    map { Highlight(it.minute, it.description) }

fun Game.toEntity() =
    GameEntity(team1Performance.toEntity(), team2Performance.toEntity(), dateTime, location, id)

fun GameWithHighlights.toModel() =
    Game(
        game.id,
        game.team1Performance.toModel(),
        game.team2Performance.toModel(),
        game.dateTime,
        game.location,
        highlights.toModel()
    )
