package com.aungkyawpaing.myanshi

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class UnicodeAnnotatedJsonClass(
    @UnicodeString @Json(name = "value") val myanmarString: MMString
)

