# Polyglot Kotlin Extension
This library is a set of extensions for Kotlin that make it easier to work with Polyglot.

## Example of usage:
```kotlin
import org.densy.polyglot.extension.*

val ENG = "en_US".toLanguage()
val RUS = "ru_RU".toLanguage()

fun main() {
    val context = translationContext {
        defaultLanguage = "en_US".toLanguage()

        globalParameter("appName", "MyApp")
        globalParameters(
            "version" to "1.0.0",
            "author" to "John Doe"
        )

        globalTranslations(ENG) {
            "common.yes" to "Yes"
            "common.no" to "No"
        }

        globalTranslations(RUS) {
            "common.yes" to "Да"
            "common.no" to "Нет"
        }
    }

    val translation = with(context) {
        translation(jsonFileProvider("lang")) {
            defaultLanguage = ENG

            translations(ENG) {
                "greeting" to "Hello, {name}!"
                "farewell" to "Goodbye, {author}!"
                "confirm" to "Are you sure, {user}? {common.yes}/{common.no}"
                "app.title" to "Welcome to {appName} v{version}"
            }
            translations(RUS) {
                "greeting" to "Привет, {name}!"
                "farewell" to "До свидания, {author}!"
                "confirm" to "Вы уверены, {user}? {common.yes}/{common.no}"
                "app.title" to "Добро пожаловать в {appName} v{version}"
            }
        }
    }

    println(translation.tr(ENG, "greeting", keyedOf("name" to "John")))
    println(translation.tr(ENG, "farewell"))
    println(translation.tr(ENG, "confirm"))
    println(translation["confirm", keyedOf("user" to "John Doe")])
}
```

## Maven
Adding repository:
```xml
<repositories>
    <repository>
        <id>densy-repository-snapshots</id>
        <url>https://repo.densy.org/snapshots</url>
    </repository>
</repositories>
```

Adding an extension:
```xml
<dependency>
    <groupId>org.densy.polyglot.extension</groupId>
    <artifactId>extension-kotlin</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

## Gradle
Adding repository:
```groovy
maven {
    name = "densyRepositorySnapshots"
    url = uri("https://repo.densy.org/snapshots")
}
```

Adding an extension:
```groovy
implementation "org.densy.polyglot.extension:extension-kotlin:1.0.0-SNAPSHOT"
```