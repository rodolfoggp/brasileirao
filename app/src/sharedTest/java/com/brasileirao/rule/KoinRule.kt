package com.brasileirao.rule

import com.brasileirao.di.brasileiraoModule
import com.brasileirao.di.testModule
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.koin.core.context.GlobalContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.ModuleDeclaration
import org.koin.dsl.module
import org.koin.test.KoinTest

open class KoinRule(
    moduleDeclaration: ModuleDeclaration = {},
) : TestWatcher(), KoinTest {

    private val extraModule = module(override = true, moduleDeclaration = moduleDeclaration)
    private var modules = listOf(brasileiraoModule, testModule, extraModule)

    override fun starting(description: Description?) {
        if (koinIsRunning().not()) { startKoin { modules(modules) } }
        loadKoinModules(modules)
    }

    override fun finished(description: Description?) {
        unloadKoinModules(modules)
        stopKoin()
    }

    private fun koinIsRunning() = GlobalContext.getOrNull() != null
}
