plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    // ktlint
    id("org.jlleitschuh.gradle.ktlint") version "12.0.3"

    // kapt
    kotlin("kapt")

    // dagger hilt
    id("com.google.dagger.hilt.android")

    // navigation
    id("androidx.navigation.safeargs.kotlin") version "2.5.2"
}

buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.49")
    }
}

android {
    namespace = "kr.hs.dgsw.mentomenv2"
    compileSdk = 34

    defaultConfig {
        applicationId = "kr.hs.dgsw.mentomenv2"
        minSdk = 26
        targetSdk = 34
        versionCode = 10001
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    // tedPermission
    implementation("io.github.ParkSangGwon:tedpermission-coroutine:3.3.0")

    // material
    implementation("com.google.android.material:material:1.7.0")

    // fragment
    implementation("androidx.fragment:fragment-ktx:1.5.5")

    // navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.2")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.2")

    // gilde
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    kapt("com.github.bumptech.glide:compiler:4.16.0")

    // dataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.9.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // dagger
    implementation("com.google.dagger:dagger:2.44.2")

    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":di"))

    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
    implementation("com.google.code.gson:gson:2.10.1")

    // hilt
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")

    // viewpager2
    implementation("androidx.viewpager2:viewpager2:1.0.0")
    implementation("com.tbuonomo:dotsindicator:4.3")

    // skeleton
    implementation("com.facebook.shimmer:shimmer:0.5.0")

    // photoview
    implementation("com.github.chrisbanes:PhotoView:2.3.0")
}

kapt {
    correctErrorTypes = true
}
