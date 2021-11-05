import Dependencies.AndroidX
import Dependencies.DI
import Dependencies.Network
import Dependencies.Cache
import Dependencies.View
import Dependencies.Coroutines
import Dependencies.Test

plugins {
    androidApplication
    kotlin(kotlinAndroid)
    kotlin(kotlinKapt)
    kotlinParcelize
    daggerHilt
}

android {
    defaultConfig {
        applicationId = Config.Android.applicationId
        minSdkVersion(Config.Version.minSdkVersion)
        compileSdkVersion(Config.Version.compileSdkVersion)
        buildToolsVersion(Config.Version.buildToolsVersion)
        targetSdkVersion(Config.Version.targetSdkVersion)
        versionCode = Config.Version.versionCode
        versionName = Config.Version.versionName
        multiDexEnabled = Config.isMultiDexEnabled
        testInstrumentationRunner = Config.Android.testInstrumentationRunner
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildTypes {

        getByName(BuildType.RELEASE) {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName(BuildType.DEBUG) {
            isMinifyEnabled = false
        }

    }

    packagingOptions {
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/LICENSE")
        exclude("META-INF/LICENSE.txt")
        exclude("META-INF/license.txt")
        exclude("META-INF/NOTICE")
        exclude("META-INF/NOTICE.txt")
        exclude("META-INF/notice.txt")
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
        exclude("META-INF/*.kotlin_module")
    }
}

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.21")
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.7")

    implementAll(Coroutines.components)

    implementAll(View.components)
    implementAll(AndroidX.components)

    implementAll(Network.components)
    Cache.run {
        api(room)
        kapt(Cache.AnnotationProcessor.room)
    }

    testImplementation(Test.junit)
    testImplementation(Test.truth)
    testImplementation(Test.coroutinesTest)
    testImplementation(Test.mockWebServer)


    implementAll(DI.components)
    kapt(DI.AnnotationProcessor.hiltAndroid)
    kapt(DI.AnnotationProcessor.hiltCompiler)
    kapt(DI.AnnotationProcessor.daggerHiltCompiler)
}