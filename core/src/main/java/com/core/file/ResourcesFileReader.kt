package com.core.file

object ResourcesFileReader {
    fun readFrom(filePath: String): String? =
        javaClass.classLoader?.getResource(filePath)?.readText()
}