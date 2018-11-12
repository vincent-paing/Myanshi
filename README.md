[![Download](https://api.bintray.com/packages/vincent-paing/maven/myanshi/images/download.svg) ](https://bintray.com/vincent-paing/maven/myanShi/_latestVersion)[![Build Status](https://travis-ci.com/vincent-paing/Myanshi.svg?branch=master)](https://travis-ci.com/vincent-paing/Myanshi)


# Myanshi

Myanshi is a [Moshi](https://github.com/square/moshi) converter that automatically converts between two popular burmese encoding, standardized unicode encoding and Zawgyi

## Usage

Initialize your Moshi instance with the following:

```kotlin
val moshi: Moshi = Moshi.Builder()
      .add(MyanShiConverter())
      //Add other adapters
      .build()
```

Then you can start using the built-in `MMString` class in your models, which holds the values for both encoding

```kotlin
@JsonClass(generateAdapter = false)
data class Person(
    @Json(name = "name") val name: MMString
)

//usage
person.name.zawgyi
person.name.unicode
```

### Helper Annotation

By default the burmese string in JSON are treated as unicode, which is preferred for storing data, as it's more standardized for sorting,searching etc.

However, you can annotate your fields to explicitly state the encoding format

```kotlin
data class Person(
    //Will convert to-and-from Zawgyi encoding
    @ZawgyiString @Json(name = "name") val name: MMString
)
```
For Unicode, use `UnicodeString` annotation

```kotlin
data class Person(
    //Will convert to-and-from Unicode encoding
    @UnicodeString @Json(name = "name") val name: MMString
)
```

For code samples, you can check inside the test folder

## Download

For gradle:

```
repositories {
    jcenter()
}

compile 'com.aungkyawpaing.myanshi:myanshi-converter:1.0.0'
```


## License

```
DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
                   Version 2, December 2004

Copyright (C) 2004 Sam Hocevar <sam@hocevar.net>

Everyone is permitted to copy and distribute verbatim or modified
copies of this license document, and changing it is allowed as long
as the name is changed.

           DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
  TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION

 0. You just DO WHAT THE FUCK YOU WANT TO.
```
