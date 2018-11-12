package com.aungkyawpaing.myanshi

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MyanShiConverterTest {

    val unicodeString =
        "သီဟိုဠ်မှ ဉာဏ်ကြီးရှင်သည် အာယုဝဍ္ဎနဆေးညွှန်းစာကို ဇလွန်ဈေးဘေး ဗာဒံပင်ထက် အဓိဋ္ဌာန်လျက် ဂဃနဏဖတ်ခဲ့သည်။"
    val zawgyiString =
        "သီဟိုဠ္မွ ဉာဏ္ႀကီးရွင္သည္ အာယုဝၯနေဆးၫႊန္းစာကို ဇလြန္ေဈးေဘး ဗာဒံပင္ထက္ အဓိ႒ာန္လ်က္ ဂဃနဏဖတ္ခဲ့သည္။"

    val unicodeJson =
        "{\"value\":\"သီဟိုဠ်မှ ဉာဏ်ကြီးရှင်သည် အာယုဝဍ္ဎနဆေးညွှန်းစာကို ဇလွန်ဈေးဘေး ဗာဒံပင်ထက် အဓိဋ္ဌာန်လျက် ဂဃနဏဖတ်ခဲ့သည်။\"}"

    val zawgyiJson =
        "{\"value\":\"သီဟိုဠ္မွ ဉာဏ္ႀကီးရွင္သည္ အာယုဝၯနေဆးၫႊန္းစာကို ဇလြန္ေဈးေဘး ဗာဒံပင္ထက္ အဓိ႒ာန္လ်က္ ဂဃနဏဖတ္ခဲ့သည္။\"}"

    val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .add(MyanShiConverter()).build()

    @Test
    fun testToJson() {
        val mmString = MMString(zawgyi = zawgyiString, unicode = unicodeString)

        val myanShiConverter = MyanShiConverter()

        val expected = unicodeString

        val actual = myanShiConverter.toJson(mmString)

        assertEquals(expected, actual)
    }

    @Test
    fun testFromJson() {
        val myanShiConverter = MyanShiConverter()

        val expected = MMString(zawgyi = zawgyiString, unicode = unicodeString)

        val actual = myanShiConverter.fromJson(unicodeString)

        assertEquals(expected, actual)
    }

    @Test
    fun testToJsonWithUnicodeAnnotation() {
        val mmString = UnicodeAnnotatedJsonClass(MMString(zawgyi = zawgyiString, unicode = unicodeString))
        val expected = unicodeJson

        val actual = moshi.adapter(UnicodeAnnotatedJsonClass::class.java).toJson(mmString)

        assertEquals(expected, actual)
    }

    @Test
    fun testFromJsonWithUnicodeAnnotation() {
        val expected = MMString(zawgyi = zawgyiString, unicode = unicodeString)

        val actual = moshi.adapter(UnicodeAnnotatedJsonClass::class.java).fromJson(unicodeJson)

        assertEquals(expected, actual!!.myanmarString)
    }


    @Test
    fun testToJsonWithZawgyiAnnotation() {
        val mmString = ZawgyiAnnotatedJsonClass(MMString(zawgyi = zawgyiString, unicode = unicodeString))
        val expected = zawgyiJson

        val actual = moshi.adapter(ZawgyiAnnotatedJsonClass::class.java).toJson(mmString)

        assertEquals(expected, actual)
    }

    @Test
    fun testFromJsonWithZawgyiAnnotation() {
        val expected = MMString(zawgyi = zawgyiString, unicode = unicodeString)

        val actual = moshi.adapter(ZawgyiAnnotatedJsonClass::class.java).fromJson(zawgyiJson)

        assertEquals(expected, actual!!.myanmarString)
    }


}
