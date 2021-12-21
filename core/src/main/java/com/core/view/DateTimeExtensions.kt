package com.core.view

import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

fun LocalDateTime.weekDayAndDateString(): String = formatAs("E dd/MM/yyyy").uppercase()

fun LocalDateTime.dateString(): String = formatAs("dd/MM/yyyy")

fun LocalDateTime.timeString(): String = formatAs("HH:mm")

private fun LocalDateTime.formatAs(format: String) =
    DateTimeFormatter.ofPattern(format)
        .format(this)