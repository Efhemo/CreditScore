import Dependencies.View.Version.fragment

const val kotlinAndroid: String = "android"
const val kotlinAndroidExtension: String = "android.extensions"
const val kotlinKapt: String = "kapt"
const val ktLintVersion: String = "0.36.0"

object Config {
    object Version {
        const val minSdkVersion: Int = 21
        const val compileSdkVersion: Int = 30
        const val targetSdkVersion: Int = 30
        const val buildToolsVersion: String = "30.0.2"
        const val versionName: String = "3.1"
        const val versionCode: Int = 9
    }

    const val isMultiDexEnabled: Boolean = true

    object Android {
        const val applicationId: String = "com.efhem.creditscore"
        const val testInstrumentationRunner: String = "androidx.test.runner.AndroidJUnitRunner"
    }
}

interface Libraries {
    val components: List<String>
}

object Dependencies {
    object AndroidX : Libraries {
        object Version {
            const val coreKtx: String = "1.5.0-alpha02"
            const val navigation: String = "2.3.0"
            const val multidex: String = "2.0.1"
            const val lifeCycle: String = "2.3.0-alpha07"
            const val activity: String = "1.2.0-alpha08"
        }

        private const val coreKtx: String = "androidx.core:core-ktx:${Version.coreKtx}"
        private const val navigationFragmentKtx: String =
            "androidx.navigation:navigation-fragment-ktx:${Version.navigation}"
        private const val navigationUiKtx: String =
            "androidx.navigation:navigation-ui-ktx:${Version.navigation}"
        private const val multiDex: String = "androidx.multidex:multidex:${Version.multidex}"
        private const val activity: String = "androidx.activity:activity:${Version.activity}"
        private const val lifeCycleCommon: String =
            "androidx.lifecycle:lifecycle-common-java8:${Version.lifeCycle}"
        private const val viewModel: String =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifeCycle}"
        private const val livedata: String =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Version.lifeCycle}"


        override val components: List<String>
            get() = listOf (
                coreKtx, navigationFragmentKtx, navigationUiKtx, multiDex, activity,
                lifeCycleCommon, viewModel, livedata
            )
    }

    object View : Libraries {
        object Version {
            const val materialComponent: String = "1.4.0"
            const val appCompat: String = "1.3.1"
            const val fragment: String = "1.3.0-alpha08"
            const val constraintLayoutVersion = "2.1.1"
        }

        const val appCompat: String = "androidx.appcompat:appcompat:${Version.appCompat}"
        const val fragment: String = "androidx.fragment:fragment-ktx:${Version.fragment}"
        const val materialComponent: String =
            "com.google.android.material:material:${Version.materialComponent}"
        private const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Version.constraintLayoutVersion}"


        override val components: List<String> =
            listOf(appCompat, fragment, materialComponent, constraintLayout )
    }

    object DI : Libraries{
        object Version  {
            const val javaxInject: String = "1"
            const val hiltAndroid: String = "2.33-beta"
            const val hiltViewModel: String = "1.0.0-alpha02"
            const val hiltNavigation: String = "1.0.0-beta01"
        }

        object AnnotationProcessor {
            const val hiltAndroid: String =
                "com.google.dagger:hilt-android-compiler:${Version.hiltAndroid}"
            const val hiltCompiler: String = "androidx.hilt:hilt-compiler:${Version.hiltNavigation}"
            const val daggerHiltCompiler: String = "com.google.dagger:hilt-compiler:${Version.hiltAndroid}"
        }

        const val javaxInject: String = "javax.inject:javax.inject:${Version.javaxInject}"
        private const val hiltAndroid: String = "com.google.dagger:hilt-android:${Version.hiltAndroid}"
        private const val hiltViewModel: String =
            "androidx.hilt:hilt-lifecycle-viewmodel:${Version.hiltViewModel}"
        private const val hiltNavigation: String =
            "androidx.hilt:hilt-navigation-fragment:${Version.hiltNavigation}"

        override val components: List<String>
            get() = listOf(javaxInject, hiltAndroid, hiltViewModel, hiltNavigation)
    }

    object Coroutines : Libraries {
        object Version {
            const val coroutines: String = "1.4.1"
        }

        const val core: String =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
        const val android: String =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"

        override val components: List<String> = listOf(core, android)
    }

    object Network : Libraries {
        object Version {
            const val okhttp: String = "4.7.2"
            const val retrofit: String = "2.9.0"
            const val moshi: String = "1.9.2"
        }

        private const val okhttp: String = "com.squareup.okhttp3:okhttp:${Version.okhttp}"
        private const val loggingInterceptor: String =
            "com.squareup.okhttp3:logging-interceptor:${Version.okhttp}"
        private const val retrofit: String = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
        private const val retrofitMoshi: String =
            "com.squareup.retrofit2:converter-moshi:${Version.retrofit}"
        const val moshi: String = "com.squareup.moshi:moshi-kotlin:${Version.moshi}"

        override val components: List<String> = listOf(
            okhttp, loggingInterceptor, retrofit,
            retrofitMoshi, moshi
        )
    }

    object Cache {
        object Version {
            const val room: String = "2.4.0-alpha01"
        }

        object AnnotationProcessor {
            const val room: String = "androidx.room:room-compiler:${Version.room}"
        }

        const val room: String = "androidx.room:room-ktx:${Version.room}"
    }

    object Test {
        object Version {
            const val junit: String = "4.13"
            const val runner: String = "1.3.0"
            const val rules: String = "1.3.0"
            const val testExt: String = "1.1.2"
            const val testCore: String = "1.3.0"
            const val espresso: String = "3.3.0"
            const val truth: String = "1.0.1"
            const val robolectric: String = "4.4"
            const val archCoreTest: String = "1.1.1"
            const val mockWebServer: String = "4.9.2"
        }

        const val junit: String = "junit:junit:${Version.junit}"
        const val runner: String = "androidx.test:runner:${Version.runner}"
        const val rules: String = "androidx.test:rules:${Version.rules}"
        const val fragmentTesting: String = "androidx.fragment:fragment-testing:$fragment"
        const val androidXTest: String = "androidx.test.ext:junit:${Version.testExt}"
        const val androidXTestCore: String = "androidx.test:core:${Version.testCore}"
        const val espresso: String = "androidx.test.espresso:espresso-core:${Version.espresso}"
        const val espressoContrib: String =
            "androidx.test.espresso:espresso-contrib:${Version.espresso}"
        const val archCoreTest: String = "android.arch.core:core-testing:${Version.archCoreTest}"
        const val truth: String = "com.google.truth:truth:${Version.truth}"
        const val coroutinesTest: String =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Coroutines.Version.coroutines}"
        const val robolectric: String = "org.robolectric:robolectric:${Version.robolectric}"
        const val mockWebServer: String =
            "com.squareup.okhttp3:mockwebserver:${Version.mockWebServer}"
    }
}

object ProjectLib {
    const val app: String = ":app"
    const val testUtils: String = ":libraries:testUtils"
}
