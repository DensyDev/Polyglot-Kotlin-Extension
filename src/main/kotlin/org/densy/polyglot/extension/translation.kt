package org.densy.polyglot.extension

import org.densy.polyglot.api.Translation
import org.densy.polyglot.api.language.Language
import org.densy.polyglot.api.parameter.TranslationParameters

/**
 * Translates using default language without parameters.
 *
 * Usage: translation.tr("hello")
 */
fun Translation.tr(key: String): String {
    return translate(defaultLanguage, key)
}

/**
 * Translates using specific language without parameters.
 *
 * Usage: translation.tr(ENG, "hello")
 */
fun Translation.tr(language: Language, key: String): String {
    return translate(language, key)
}

/**
 * Translates with parameters using default language.
 *
 * Usage: translation.tr("hello", keyedOf("name" to "John"))
 */
fun Translation.tr(key: String, parameters: TranslationParameters): String {
    return translate(defaultLanguage, key, parameters)
}

/**
 * Translates with parameters using specific language.
 *
 * Usage: translation.tr(ENG, "hello", keyedOf("name" to "John"))
 */
fun Translation.tr(language: Language, key: String, parameters: TranslationParameters): String {
    return translate(language, key, parameters)
}

/**
 * Translates with array parameters using default language.
 *
 * Usage: translation.tr("hello", arrayOf("John", 25))
 */
fun Translation.tr(key: String, vararg parameters: Any): String {
    return translate(defaultLanguage, key, parameters)
}

/**
 * Translates with array parameters using specific language.
 *
 * Usage: translation.tr(ENG, "hello", arrayOf("John", 25))
 */
fun Translation.tr(language: Language, key: String, vararg parameters: Any): String {
    return translate(language, key, parameters)
}

/**
 * Translates using default language.
 *
 * Usage: translation["hello"]
 */
operator fun Translation.get(key: String): String {
    return translate(defaultLanguage, key)
}

/**
 * Translates with parameters using default language.
 *
 * Usage: translation["hello", keyedOf("name" to "John Doe")]
 */
operator fun Translation.get(key: String, parameters: TranslationParameters): String {
    return translate(defaultLanguage, key, parameters)
}

/**
 * Translates with array parameters using default language.
 *
 * Usage: translation["hello", arrayOf("John", 25)]
 */
operator fun Translation.get(key: String, vararg parameters: Any): String {
    return translate(defaultLanguage, key, parameters)
}

/**
 * Translates using specific language.
 *
 * Usage: translation[ENG, "hello"]
 */
operator fun Translation.get(language: Language, key: String): String {
    return translate(language, key)
}

/**
 * Translates with parameters using specific language.
 *
 * Usage: translation[ENG, "hello", keyedOf("name" to "John Doe")]
 */
operator fun Translation.get(language: Language, key: String, parameters: TranslationParameters): String {
    return translate(language, key, parameters)
}

/**
 * Translates with array parameters using specific language.
 *
 * Usage: translation[ENG, "hello", arrayOf("John Doe", 25)]
 */
operator fun Translation.get(language: Language, key: String, vararg parameters: Any): String {
    return translate(language, key, parameters)
}

/**
 * Adds translation for specific language.
 *
 * Usage: translation.add(ENG, "generic.yes" to "Yes")
 */
fun Translation.add(language: Language, pair: Pair<String, String>) {
    addTranslation(language, pair.first, pair.second)
}

/**
 * Adds several translations for specific language.
 *
 * Usage: translation.addAll(ENG, "generic.yes" to "Yes", "generic.no" to "No")
 */
fun Translation.addAll(language: Language, vararg pairs: Pair<String, String>) {
    pairs.forEach { (key, value) ->
        addTranslation(language, key, value)
    }
}