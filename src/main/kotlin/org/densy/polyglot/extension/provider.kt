package org.densy.polyglot.extension

import org.densy.polyglot.api.context.TranslationContext
import org.densy.polyglot.api.provider.TranslationProvider
import org.densy.polyglot.core.provider.JsonFileProvider
import org.densy.polyglot.core.provider.YamlFileProvider
import java.io.File

/**
 * Creates a TranslationProvider that loads translations from a JSON file.
 *
 * @param fileName the path to the JSON file.
 * @return a new JsonFileProvider instance with the current context's language standard.
 */
context(context: TranslationContext)
fun jsonFileProvider(fileName: String): TranslationProvider {
    return jsonFileProvider(File(fileName))
}

/**
 * Creates a TranslationProvider that loads translations from a JSON file.
 *
 * @param file the JSON File object.
 * @return a new JsonFileProvider instance with the current context's language standard.
 */
context(context: TranslationContext)
fun jsonFileProvider(file: File): TranslationProvider {
    return JsonFileProvider(file, context.languageStandard)
}

/**
 * Creates a TranslationProvider that loads translations from a YAML file.
 *
 * @param fileName the path to the YAML file.
 * @return a new YamlFileProvider instance with the current context's language standard.
 */
context(context: TranslationContext)
fun yamlFileProvider(fileName: String): TranslationProvider {
    return yamlFileProvider(File(fileName))
}

/**
 * Creates a TranslationProvider that loads translations from a YAML file.
 *
 * @param file the YAML File object.
 * @return a new YamlFileProvider instance with the current context's language standard.
 */
context(context: TranslationContext)
fun yamlFileProvider(file: File): TranslationProvider {
    return YamlFileProvider(file, context.languageStandard)
}