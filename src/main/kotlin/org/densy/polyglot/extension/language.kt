package org.densy.polyglot.extension

import org.densy.polyglot.api.language.Language
import org.densy.polyglot.core.language.BaseLanguage
import java.util.*

/**
 * Converts string to BaseLanguage.
 *
 * Usage: "en_GB".toLanguage()
 */
fun String.toLanguage(): Language {
    return BaseLanguage.parseLanguage(this)
}

/**
 * Safely converts string to BaseLanguage or null if invalid.
 */
fun String.toLanguageOrNull(): Language? {
    return try {
        BaseLanguage.parseLanguage(this)
    } catch (_: Exception) {
        null
    }
}

/**
 * Converts Locale to BaseLanguage.
 *
 * Usage: Locale.US.toLanguage()
 */
fun Locale.toLanguage(): Language {
    return BaseLanguage.parseLocale(this)
}

/**
 * Safely converts Locale to BaseLanguage or null if invalid.
 */
fun Locale.toLanguageOrNull(): Language? {
    return try {
        BaseLanguage.parseLocale(this)
    } catch (e: Exception) {
        null
    }
}

/**
 * Converts Language to Locale.
 *
 * Usage: language.toLocale()
 */
fun Language.toLocale(): Locale {
    return if (countryCode != null) {
        Locale(languageCode, countryCode)
    } else {
        Locale(languageCode)
    }
}
