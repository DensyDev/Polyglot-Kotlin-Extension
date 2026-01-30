package org.densy.polyglot.extension

import org.densy.polyglot.api.Translation
import org.densy.polyglot.api.TranslationsAware
import org.densy.polyglot.api.context.TranslationContext
import org.densy.polyglot.api.formatter.TranslationFormatter
import org.densy.polyglot.api.formatter.TranslationFormatterAware
import org.densy.polyglot.api.language.Language
import org.densy.polyglot.api.provider.TranslationProvider
import org.densy.polyglot.api.util.FallbackStrategy
import org.densy.polyglot.api.util.LanguageStrategy

/**
 * Creates a new Translation from context.
 */
fun TranslationContext.translation(): Translation {
    return createTranslation()
}

/**
 * Creates a new Translation with provider.
 *
 * @param provider translation provider
 */
fun TranslationContext.translation(provider: TranslationProvider): Translation {
    return createTranslation(provider)
}

/**
 * Creates a new Translation with configuration.
 */
fun TranslationContext.translation(block: TranslationConfig.() -> Unit): Translation {
    val translation = createTranslation()
    TranslationConfig(translation).apply(block)
    return translation
}

/**
 * Creates a new Translation with provider and configuration.
 *
 * @param provider translation provider
 */
fun TranslationContext.translation(
    provider: TranslationProvider,
    block: TranslationConfig.() -> Unit
): Translation {
    val translation = createTranslation(provider)
    TranslationConfig(translation).apply(block)
    return translation
}

/**
 * Configures existing Translation.
 */
fun Translation.configure(block: TranslationConfig.() -> Unit) {
    TranslationConfig(this).apply(block)
}

/**
 * Translation configurer.
 */
class TranslationConfig(private val translation: Translation) {

    /**
     * Sets the default language.
     */
    var defaultLanguage: Language
        get() = translation.defaultLanguage
        set(value) {
            translation.defaultLanguage = value
        }

    /**
     * Sets the language strategy.
     */
    var languageStrategy: LanguageStrategy?
        get() = null // Translation interface doesn't have getter
        set(value) {
            if (value != null) {
                translation.setLanguageStrategy(value)
            }
        }

    /**
     * Sets the fallback strategy.
     */
    var fallbackStrategy: FallbackStrategy?
        get() = null // Translation interface doesn't have getter
        set(value) {
            if (value != null) {
                translation.setFallbackStrategy(value)
            }
        }

    /**
     * Adds a formatter.
     */
    fun formatter(formatter: TranslationFormatter) {
        translation.addFormatter(formatter)
    }

    /**
     * Adds multiple formatters.
     */
    fun formatters(vararg formatters: TranslationFormatter) {
        formatters.forEach { translation.addFormatter(it) }
    }

    /**
     * DSL for adding formatters.
     */
    fun formatters(block: FormattersScope.() -> Unit) {
        FormattersScope(translation).apply(block)
    }

    /**
     * Adds a single translation.
     */
    fun translation(language: Language, key: String, value: String) {
        translation.addTranslation(language, key, value)
    }

    /**
     * Adds multiple translations for a language.
     */
    fun translations(language: Language, vararg pairs: Pair<String, String>) {
        pairs.forEach { (key, value) ->
            translation.addTranslation(language, key, value)
        }
    }

    /**
     * Adds multiple translations from map.
     */
    fun translations(language: Language, translations: Map<String, String>) {
        translation.addTranslations(language, translations)
    }

    /**
     * DSL for adding translations.
     */
    fun translations(language: Language, block: TranslationsScope.() -> Unit) {
        TranslationsScope(translation, language).apply(block)
    }
}

/**
 * Formatters aware scope.
 */
class FormattersScope(private val translation: TranslationFormatterAware) {
    operator fun TranslationFormatter.unaryPlus() {
        translation.addFormatter(this)
    }

    fun add(formatter: TranslationFormatter) {
        translation.addFormatter(formatter)
    }
}

/**
 * Translations aware scope.
 */
class TranslationsScope(
    private val translation: TranslationsAware,
    private val language: Language
) {
    infix fun String.to(value: String) {
        translation.addTranslation(language, this, value)
    }
}