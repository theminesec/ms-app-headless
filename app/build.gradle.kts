import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName
import org.jetbrains.kotlin.utils.filterIsInstanceAnd
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.msa.headless"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.msa.headless.bctc"
        minSdk = 26
        targetSdk = 34
        /**
         * Version pattern
         * A.BB.CCC.DDD(MMM)
         * For MPoC starting from 2.00.000.DDD(MMM)
         * Normally A.BB should rarely change and should be part of the wildcard versioning
         *
         * A -
         * Major version change: Complete architecture changes of the application
         * Change would trigger full certification.
         *
         * BB -
         * Minor version change: Changes that might impact scope of certified product.
         * Change of BB will require delta certification on the next renewal of the solution
         *
         * CCC -
         * Functions version change: Bug fixes, new SDK version, changes in sub-core components, UI updates…
         * Change of CCC will NOT require any re-certification.
         *
         * DDD - First 3 digits of the build number
         * MMM - First 3 digits of CustomerID
         */
        // DO NOT CHANGE, re-cert require
        val majorVersion = 2
        // DO NOT CHANGE, re-cert require
        val minorVersion = 0
        // msa component lib version
        val patchVersion: Int = "008".toInt()
        // license update, build config changes
        val buildVersion = 1
        // Neurogine
        val customerId = 990

        println("majorVersion: $majorVersion,\nminorVersion: $minorVersion,\npatchVersion: $patchVersion,\nbuildVersion: $buildVersion,\ncustomerId: $customerId")

        val verCode = 0
            .plus(majorVersion.times(1_00_000_000))
            .plus(minorVersion.times(1_000_000))
            .plus(patchVersion.times(1_000))
            .plus(buildVersion)

        println("verCode: $verCode")

        val verName = "$majorVersion" +
                ".${minorVersion.toString().padStart(2, '0')}" +
                ".${patchVersion.toString().padStart(3, '0')}" +
                ".${buildVersion.toString().padStart(3, '0')}" +
                "(${customerId.toString().padStart(3, '0')})"

        versionCode = verCode
        versionName = verName

        val formatter = DateTimeFormatter.ofPattern("yyMMdd_hhmm")
        val currentTime = LocalDateTime.now().format(formatter)
        archivesName = "$applicationId-$versionName-${currentTime}"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"

            excludes += "/META-INF/DEPENDENCIES"
            excludes += "/META-INF/LICENSE"
            excludes += "/META-INF/LICENSE.txt"
            excludes += "/META-INF/license.txt"
            excludes += "/META-INF/NOTICE"
            excludes += "/META-INF/NOTICE.txt"
            excludes += "/META-INF/notice.txt"
            excludes += "/META-INF/ASL2.0"
            excludes += "/META-INF/*.kotlin_module"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation(project(":lib-model"))
    implementation(platform(libs.compose.bom))
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation ("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    implementation(platform(libs.compose.bom))
    implementation(platform(libs.compose.bom))
    implementation(platform(libs.compose.bom))
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(platform(libs.compose.bom))
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp)
    androidTestImplementation(platform(libs.compose.bom))
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation(libs.androidx.constraintlayout.compose)
    implementation("com.google.android.material:material:1.12.0")
    implementation(libs.flexbox)

    implementation ("androidx.compose.runtime:runtime-livedata:1.0.0")
    implementation(libs.google.gson.v2101)
    // jetpack compose navigation
    implementation(libs.navigation.compose)

    implementation(libs.visa.sensory)
    implementation(libs.signaturepad)

    //implementation("com.theminesec.sdk:headless-stage:1.0.17")
//    implementation("com.theminesec.sdk:headless-mpoc-stage:2.0.1")
    implementation("com.theminesec.sdk:headless-mpoc:2.0.1")

}