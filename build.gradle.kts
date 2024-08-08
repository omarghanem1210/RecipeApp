// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript{
    plugins {
        kotlin("jvm") version "1.8.10" // Define Kotlin version compatible with Compose
    }

    repositories {
        google()
        mavenCentral()
    }
    dependencies{
        classpath("androidx.navigation.safeargs:androidx.navigation.safeargs.gradle.plugin:2.7.7")
        classpath ("com.android.tools.build:gradle:7.3.1")
    }
}
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id("androidx.navigation.safeargs.kotlin")version "2.7.7" apply false
}