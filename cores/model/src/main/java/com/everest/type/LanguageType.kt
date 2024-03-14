package com.everest.type

enum class LanguageType {
    en,
    zh
}


private object LanguageTypeValue {
    const val EN = 0
    const val ZH = 1
}

fun LanguageType.value(): Int {
    return when (this) {
        LanguageType.en -> LanguageTypeValue.EN
        LanguageType.zh -> LanguageTypeValue.ZH
    }
}



fun Int.toLanguageType(): LanguageType {
    return when (this) {
        LanguageTypeValue.EN -> LanguageType.en
        LanguageTypeValue.ZH -> LanguageType.zh
        else ->  LanguageType.en
    }
}
