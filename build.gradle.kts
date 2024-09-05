// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("maven-publish")
    alias(libs.plugins.kotlin.android) apply false
    // alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
    alias(libs.plugins.gms.googleServices) apply false
    // alias(libs.plugins.kotlin.serialization) apply false
}

allprojects {
    configurations.all {
        resolutionStrategy {
            preferProjectModules()
            cacheChangingModulesFor(0, "seconds")
        }
    }
//
//    val majorVersion: String by project
//    val minorVersion: String by project
//    val patchVersion: String by project
//    // val buildSnapshot: String? by project
//
//    group = "com.theminesec.app"
//    version = "$majorVersion.$minorVersion.$patchVersion"
}

subprojects {
    plugins.withType<MavenPublishPlugin> {
        publishing {
            repositories {
                maven {
                    val GITHUB_USERNAME: String by project
                    val GITHUB_TOKEN: String by project
                    println("GPR publish username: $GITHUB_USERNAME")
                    name = "gprInternal"
                    url = uri("https://maven.pkg.github.com/theminesec/ms-registry-internal")
                    credentials {
                        username = GITHUB_USERNAME
                        password = GITHUB_TOKEN
                    }
                }
            }
            publications {
                register<MavenPublication>("aar") {
                    artifact("$buildDir/outputs/aar/${project.name}-release.aar")
                }
            }
        }
    }
}
