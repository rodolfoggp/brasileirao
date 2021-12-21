package com.example.testing_library.koin

import org.koin.core.context.loadKoinModules
import org.koin.dsl.ModuleDeclaration
import org.koin.dsl.module

fun overrideInKoin(moduleDeclaration: ModuleDeclaration) {
    loadKoinModules(
        module(override = true, moduleDeclaration = moduleDeclaration)
    )
}

/**
 * Use this function in koin test files where you want to
 * inject an instance of a class and cast it to another.
 * Example:
 * val dog by inject<Animal>().asClass<Dog>()
 */
fun <S> Lazy<Any>.asClass() = this as Lazy<S>