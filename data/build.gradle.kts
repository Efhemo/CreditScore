import Dependencies.Network
import Dependencies.Test
import ProjectLib.domain

plugins {
    kotlinLibrary
    kotlin(kotlinKapt)
}

dependencies {
    implementation(project(domain))

    implementAll(Network.components)

    kapt(Network.AnnotationProcessor.moshi)

    testImplementation(Test.mockWebServer)
    testImplementation(Test.junit)
    testImplementation(Test.truth)
    testImplementation(Test.coroutinesTest)
}
