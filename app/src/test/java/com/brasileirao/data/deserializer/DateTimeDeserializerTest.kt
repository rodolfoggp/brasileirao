package com.brasileirao.data.deserializer

import com.brasileirao.rule.KoinRule
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.koin.core.component.inject
import org.koin.test.KoinTest
import org.threeten.bp.LocalDateTime

class DateTimeDeserializerTest: KoinTest {

    @get:Rule
    val rule = KoinRule()

    private val gson: Gson by inject()

    private class TimeContainer(val time: LocalDateTime)

    @Test
    fun `deserializer Should turn ISO date time string into LocalDateTime`() {
        // Given
        val json = "{\"time\":\"2021-11-11T00:00:00.000\"}"
        val expectedLocalDateTime = LocalDateTime.of(
            2021, 11, 11, 0, 0
        )

        // When
        val timeContainer = gson.fromJson(json, TimeContainer::class.java)

        // Then
        assertEquals(expectedLocalDateTime, timeContainer.time)
    }
}