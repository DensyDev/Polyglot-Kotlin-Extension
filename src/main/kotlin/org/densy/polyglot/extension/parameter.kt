package org.densy.polyglot.extension

import org.densy.polyglot.core.parameter.ArrayTranslationParameters
import org.densy.polyglot.core.parameter.KeyedTranslationParameters

/**
 * Creates keyed parameters.
 *
 * Usage: keyedOf("name" to "John", "age" to 25)
 */
fun keyedOf(vararg pairs: Pair<String, Any>): KeyedTranslationParameters {
    return KeyedTranslationParameters(pairs.toMap())
}

/**
 * Creates keyed parameters from map.
 */
fun keyedOf(map: Map<String, Any>): KeyedTranslationParameters {
    return KeyedTranslationParameters(map)
}

/**
 * Creates array parameters.
 *
 * Usage: arrayOf("John", 25, "Admin")
 */
fun arrayOf(vararg values: Any): ArrayTranslationParameters {
    return ArrayTranslationParameters(values)
}

/**
 * Creates array parameters from list.
 */
fun arrayOf(values: List<Any>): ArrayTranslationParameters {
    return ArrayTranslationParameters(values.toTypedArray())
}