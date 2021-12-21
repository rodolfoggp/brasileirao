package com.brasileirao.robot

import com.brasileirao.data.api.RESPONSE_PATH
import com.brasileirao.data.api.mapper.toGames
import com.brasileirao.data.api.model.GamesResponse
import com.brasileirao.domain.model.Game
import com.core.file.ResourcesFileReader
import com.example.testing_library.robot.RobotBlock
import com.example.testing_library.robot.robotFunction
import com.google.gson.Gson
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal fun <T> games(block: RobotBlock<GamesRobot, T>) = robotFunction(block)

@OptIn(KoinApiExtension::class)
class GamesRobot: KoinComponent {
    private val gson: Gson by inject()

    val gamesResponseFromServer: GamesResponse get() {
        val json = ResourcesFileReader.readFrom(RESPONSE_PATH)!!
        return gson.fromJson(json, GamesResponse::class.java)
    }

    val expectedGames: List<Game> get() = gamesResponseFromServer.toGames()
}