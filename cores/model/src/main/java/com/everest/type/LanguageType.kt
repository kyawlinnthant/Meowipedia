package com.everest.type

enum class LanguageType {
    en,
    fr
}


private object LanguageIntTypeValue {
    const val EN = 0
    const val FR = 1
}


private object LanguageStringTypeValue {
    const val EN = "en"
    const val FR = "fr"
}

fun LanguageType.value(): Int {
    return when (this) {
        LanguageType.en -> LanguageIntTypeValue.EN
        LanguageType.fr -> LanguageIntTypeValue.FR
    }
}


fun Int.toLanguageType(): LanguageType {
    return when (this) {
        LanguageIntTypeValue.EN -> LanguageType.en
        LanguageIntTypeValue.FR -> LanguageType.fr
        else -> LanguageType.en
    }
}


fun String.toStringLanguageType(): LanguageType {
    return when (this) {
        LanguageStringTypeValue.EN -> LanguageType.en
        LanguageStringTypeValue.FR -> LanguageType.fr
        else -> LanguageType.en
    }
}
