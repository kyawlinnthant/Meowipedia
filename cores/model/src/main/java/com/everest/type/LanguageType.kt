package com.everest.type

enum class LanguageType {
    EN,
    FR
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
        LanguageType.EN -> LanguageIntTypeValue.EN
        LanguageType.FR -> LanguageIntTypeValue.FR
    }
}

fun Int.toLanguageType(): LanguageType {
    return when (this) {
        LanguageIntTypeValue.EN -> LanguageType.EN
        LanguageIntTypeValue.FR -> LanguageType.FR
        else -> LanguageType.EN
    }
}

fun String.toStringLanguageType(): LanguageType {
    return when (this) {
        LanguageStringTypeValue.EN -> LanguageType.EN
        LanguageStringTypeValue.FR -> LanguageType.FR
        else -> LanguageType.EN
    }
}
