package com.aungkyawpaing.myanshi

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class Person(
    //Will convert to-and-from Unicode encoding
    @UnicodeString @Json(name = "name") val name: MMString
)