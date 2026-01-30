package org.densy.polyglot.extension

import org.densy.polyglot.api.context.TranslationContext
import org.densy.polyglot.api.language.Language
import org.densy.polyglot.api.language.LanguageStandard
import org.densy.polyglot.core.context.BaseTranslationContext

/**
 * Creates a new TranslationContext.
 */
fun translationContext(): TranslationContext {
    return BaseTranslationContext()
}

/**
 * Creates a new TranslationContext with configuration.
 */
fun translationContext(block: TranslationContextConfig.() -> Unit): TranslationContext {
    val context = BaseTranslationContext()
    TranslationContextConfig(context).apply(block)
    return context
}

/**
 * Translation context configurer.
 */
class TranslationContextConfig(private val context: TranslationContext) {

    /**
     * Sets the default language.
     */
    var defaultLanguage: Language
        get() = context.defaultLanguage
        set(value) {
            context.defaultLanguage = value
        }

    /**
     * Sets the language standard.
     */
    var languageStandard: LanguageStandard
        get() = context.languageStandard
        set(value) {
            context.languageStandard = value
        }

    /**
     * Adds a single global parameter.
     */
    fun globalParameter(key: String, value: Any) {
        context.addGlobalParameter(key, value)
    }

    /**
     * Adds multiple global parameters.
     */
    fun globalParameters(vararg pairs: Pair<String, Any>) {
        pairs.forEach { (key, value) ->
            context.addGlobalParameter(key, value)
        }
    }

    /**
     * Adds multiple global parameters from map.
     */
    fun globalParameters(parameters: Map<String, Any>) {
        context.addGlobalParameters(parameters)
    }

    /**
     * Adds a single global translation.
     */
    fun globalTranslation(language: Language, key: String, value: String) {
        context.addGlobalTranslation(language, key, value)
    }

    /**
     * Adds multiple global translations for a language.
     */
    fun globalTranslations(language: Language, vararg pairs: Pair<String, String>) {
        pairs.forEach { (key, value) ->
            context.addGlobalTranslation(language, key, value)
        }
    }

    /**
     * Adds multiple global translations from map.
     */
    fun globalTranslations(language: Language, translations: Map<String, String>) {
        context.addGlobalTranslations(language, translations)
    }

    /**
     * DSL for adding global parameters.
     */
    fun globalParameters(block: GlobalParametersScope.() -> Unit) {
        GlobalParametersScope(context).apply(block)
    }

    /**
     * DSL for adding global translations.
     */
    fun globalTranslations(language: Language, block: GlobalTranslationsScope.() -> Unit) {
        GlobalTranslationsScope(context, language).apply(block)
    }
}

/**
 * Global context translation parameters scope.
 */
class GlobalParametersScope(private val context: TranslationContext) {
    infix fun String.to(value: Any) {
        context.addGlobalParameter(this, value)
    }
}

/**
 * Global context translations scope.
 */
class GlobalTranslationsScope(
    private val context: TranslationContext,
    private val language: Language
) {
    infix fun String.to(value: String) {
        context.addGlobalTranslation(language, this, value)
    }
}