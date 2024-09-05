plugins {
    id("maven-publish")
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.minesec.msa.client.sdk"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {

    ////////// Android //////////
    // lib module no need UI?
    // implementation(libs.androidx.appcompat)
    // implementation(libs.material)

    ////////// Network & Serialization //////////
    //okhttp3 + retrofit dependencies
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp)
    implementation(libs.okhttp.loggingInterceptor)
    implementation(libs.okhttp.tls)
    implementation(libs.retrofit)
    implementation(libs.retrofit.rxjava2)
    implementation(libs.retrofit.gsonconverter)

    ////////// Annotations, compiler //////////
    implementation(libs.androidx.annotation)
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)

    ////////// Test //////////
    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso)

    implementation ("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")

}

