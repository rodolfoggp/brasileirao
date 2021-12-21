package com.example.testing_library.robot

import org.koin.core.context.GlobalContext

typealias RobotBlock<T, R> = T.() -> R

inline fun <reified T : Any, R> robotFunction(block: RobotBlock<T, R>): R {
    val robotClass = T::class
    val robotInstance = GlobalContext.get().get(robotClass)
    return robotInstance.block()
}