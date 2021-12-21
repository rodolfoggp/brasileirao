package com.brasileirao.data.deserializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.lang.reflect.Type

class DateTimeDeserializer : JsonDeserializer<LocalDateTime> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): LocalDateTime? {
        val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
        return formatter.parse(json.asString, LocalDateTime.FROM)
    }
}