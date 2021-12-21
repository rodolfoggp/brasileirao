package com.brasileirao.di

import androidx.room.Room
import com.brasileirao.data.api.FakeGamesService
import com.brasileirao.data.api.GamesService
import com.brasileirao.data.database.GamesDatabase
import com.brasileirao.data.datasource.GamesDatabaseDataSource
import com.brasileirao.data.datasource.GamesLocalDataSource
import com.brasileirao.data.datasource.GamesRemoteDataSource
import com.brasileirao.data.datasource.GamesServiceDataSource
import com.brasileirao.data.deserializer.DateTimeDeserializer
import com.brasileirao.data.repository.GamesRepositoryImpl
import com.brasileirao.domain.repository.GamesRepository
import com.brasileirao.presentation.details.GameDetailsViewModel
import com.brasileirao.presentation.list.GamesListViewModel
import com.core.resource.ResourceProvider
import com.google.gson.GsonBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.threeten.bp.LocalDateTime

val brasileiraoModule = module {
    viewModel { GamesListViewModel(repository = get(), resourceProvider = get()) }
    viewModel { (gameId: Long) ->
        GameDetailsViewModel(
            gameId = gameId,
            gamesRepository = get(),
            resourceProvider = get()
        )
    }

    single {
        GsonBuilder()
            .registerTypeAdapter(LocalDateTime::class.java, DateTimeDeserializer())
            .create()
    }

    factory<GamesRemoteDataSource> { GamesServiceDataSource(service = get()) }
    factory<GamesLocalDataSource> { GamesDatabaseDataSource(gameDao = get()) }
    factory<GamesRepository> {
        GamesRepositoryImpl(
            localDataSource = get(),
            remoteDataSource = get(),
        )
    }

    factory<GamesService> { FakeGamesService(gson = get()) }
    factory { get<GamesDatabase>().getGameDao() }

    single {
        Room.databaseBuilder(
            get(),
            GamesDatabase::class.java, "games_database"
        ).build()
    }

    factory { ResourceProvider(androidContext()) }
}