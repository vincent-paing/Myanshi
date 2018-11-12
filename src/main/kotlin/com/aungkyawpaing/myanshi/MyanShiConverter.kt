package com.aungkyawpaing.myanshi

import com.aungkyawpaing.rabbkt.RabbktConverter
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class MyanShiConverter {

    @ToJson
    fun toJson(mmString: MMString): String {
        return mmString.unicode
    }

    @FromJson
    fun fromJson(string: String): MMString {
        return MMString(
            zawgyi = RabbktConverter.unicodeToZawgyi(string),
            unicode = string
        )
    }

    @ToJson
    fun toJsonWithUnicodeAnnotation(@UnicodeString mmString: MMString): String {
        return mmString.unicode
    }

    @FromJson
    @UnicodeString
    fun fromJsonWithUnicodeAnnotation(string: String): MMString {
        return MMString(
            zawgyi = RabbktConverter.unicodeToZawgyi(string),
            unicode = string
        )
    }

    @ToJson
    fun toJsonWithZawgyiAnnotation(@ZawgyiString mmString: MMString): String {
        return mmString.zawgyi
    }


    @FromJson
    @ZawgyiString
    fun fromJsonWithZawgyiAnnotation(string: String): MMString {
        return MMString(
            zawgyi = string,
            unicode = RabbktConverter.zawgyiToUnicode(string)
        )
    }
}
