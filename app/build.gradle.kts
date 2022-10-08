plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.junwooyeom.meatapplication"
    compileSdk = libs.versions.compilesdk.get().toInt()

    defaultConfig {
        applicationId = "com.junwooyeom.meatapplication"
        minSdk = libs.versions.minsdk.get().toInt()
        targetSdk = libs.versions.targetsdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {

    // AndroidX
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime)

    // Bundle Implementation
    implementation(libs.bundles.compose)
    implementation(libs.bundles.accompanist)
    implementation(libs.bundles.retrofit)

    // Coroutine Implementation
    implementation(libs.coroutine)

    // Android Navigation Implementation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation)

    // Room Implementation
    implementation(libs.room)
    kapt(libs.room.compiler)

    // Coil Implementation ( Use
    implementation(libs.coil)

    // Hilt
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    // TEST IMPLEMENTATION
    androidTestImplementation(libs.bundles.compose.hilt.test)
    kaptAndroidTest(libs.hilt.compiler)

    // IMPLEMENTATION ONLY FOR DEBUG
    debugImplementation(libs.bundles.compose.debug)
}
