plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.navigation.safeargs)
    alias(libs.plugins.compose.compiler)

}
apply {
    from("${rootDir}/shared.gradle")
}
android {
    namespace = "com.example.features"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures{
        dataBinding = true
        viewBinding = true
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.shimmer)
    implementation(libs.glide)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
    implementation(libs.androidx.navigation.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.navigation.ui.ktx)
    implementation(libs.androidx.foundation.layout)
    implementation(libs.androidx.compose.foundation.layout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(project(path = ":core:resources"))
    implementation(project(path = ":core:network"))
    implementation(project(path = ":core:common"))
    implementation(project(path = ":core:data"))
    val composeBom = platform("androidx.compose:compose-bom:2024.06.00")
    implementation(composeBom)

    // Core Compose UI
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")

    // Material Design 3 components (buttons, cards, etc.)
    implementation("androidx.compose.material3:material3")

    // Lets Activities work with Compose
    implementation("androidx.activity:activity-compose:1.9.0")
    // Debug tools (live preview in Android Studio)
    debugImplementation("androidx.compose.ui:ui-tooling")
    implementation("io.coil-kt.coil3:coil-compose:3.1.0")
    // Needed for loading images from network URLs with Coil 3
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.1.0")
    implementation(libs.androidx.compose.runtime)
    implementation("com.airbnb.android:lottie-compose:6.0.0")

}