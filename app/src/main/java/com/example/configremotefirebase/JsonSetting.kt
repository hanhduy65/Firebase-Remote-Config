package com.example.configremotefirebase

import com.google.gson.reflect.TypeToken
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.lang.reflect.Type

@JsonClass(generateAdapter = true)
data class JsonSetting(
    @field:Json(name = "commonInfo")
    var commonInfo: CommonInfo? = null,
) {
    companion object {
        val type: Type
            get() = object : TypeToken<JsonSetting?>() {}.type
    }
}