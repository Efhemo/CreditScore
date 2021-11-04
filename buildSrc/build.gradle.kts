import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    mavenCentral()
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
}


val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    languageVersion = Plugin.Version.kotlin
}

object Plugin {
    object Version {
        const val kotlin: String = "1.4.20"
        const val androidGradle: String = "4.1.1"
        const val navigation: String = "2.3.0"
        const val daggerHiltAndroid: String = "2.33-beta"
        const val spotless: String = "4.0.1"
        const val smileIdSdkPluginVersion = "1.0.1"
        const val googleServiceVersion = "4.3.10"
        const val crashlyticsVersion = "2.7.1"
    }

    const val kotlin: String = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"
    const val androidGradle: String = "com.android.tools.build:gradle:${Version.androidGradle}"
    const val navigationSafeArgs: String =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Version.navigation}"
    const val daggerHilt: String =
        "com.google.dagger:hilt-android-gradle-plugin:${Version.daggerHiltAndroid}"
    const val spotless: String = "com.diffplug.spotless:spotless-plugin-gradle:${Version.spotless}"
    const val smileId: String = "com.smileidentity:smile-id-android:${Version.smileIdSdkPluginVersion}"
    const val googleService: String = "com.google.gms:google-services:${Version.googleServiceVersion}"
    const val googleCrashlytics: String = "com.google.firebase:firebase-crashlytics-gradle:${Version.crashlyticsVersion}"
}

dependencies {
    implementation(Plugin.kotlin)
    implementation(Plugin.androidGradle)
    implementation(Plugin.navigationSafeArgs)
    implementation(Plugin.daggerHilt)
    implementation(Plugin.spotless)
    implementation(Plugin.smileId)
    implementation(Plugin.googleService)
    implementation(Plugin.googleCrashlytics)
}
