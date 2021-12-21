package com.brasileirao.di

import com.brasileirao.data.api.GamesService
import com.brasileirao.data.api.TestGamesService
import com.brasileirao.data.database.dao.FakeGameDao
import com.brasileirao.data.database.dao.GameDao
import com.brasileirao.robot.GamesRobot
import com.core.resource.ResourceProvider
import io.mockk.mockk
import org.koin.dsl.module

val testModule = module(override = true) {
    factory { GamesRobot() }
    single<GameDao> { FakeGameDao() }
    single<GamesService> { TestGamesService(gson = get()) }
    single<ResourceProvider> { mockk() }
}