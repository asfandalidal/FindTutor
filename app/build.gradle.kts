plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.findtutor"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.findtutor"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            buildConfigField ("String", "web_client_id", "\"${System.getenv("web_client_id")}\"")
        }
        release {
            buildConfigField ("String", "web_client_id", "\"${System.getenv("web_client_id")}\"")
            isMinifyEnabled = false
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
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}


dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.firebase.auth.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    //Hilt
    implementation ("com.google.dagger:hilt-android:2.47")
    kapt ("com.google.dagger:hilt-android-compiler:2.47")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation ("androidx.compose.ui:ui-text-google-fonts:1.4.0")
    implementation ("androidx.compose.material:material-icons-extended:1.6.0")
    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    // Converter for JSON (e.g., GSON)
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    // OkHttp (Core)
    implementation ("com.squareup.okhttp3:okhttp:4.11.0")
    // OkHttp Logging Interceptor
    implementation ("com.squareup.okhttp3:logging-interceptor:4.11.0")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.7.3")
    // Coroutines Core (Common library for Kotlin)
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    // Coroutines Android
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    // Coil for Compose
    implementation("io.coil-kt.coil3:coil-compose:3.0.4")
    //Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.7.0"))
    implementation ("com.google.firebase:firebase-auth:21.0.9")
    implementation ("com.google.android.gms:play-services-auth:20.6.0")
    implementation ("androidx.security:security-crypto:1.0.0")
    implementation("androidx.compose.material:material:1.7.6")
    implementation ("com.google.firebase:firebase-firestore-ktx")
    implementation ("androidx.navigation:navigation-compose:2.7.3")
    // Accompanist System UI Controller
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.30.0")
    implementation ("com.google.accompanist:accompanist-pager:0.32.0")
    implementation ("com.google.accompanist:accompanist-permissions:0.30.0")

    // bottom nav bar lib
    implementation ("com.github.PratikFagadiya:AnimatedSmoothBottomNavigation-JetpackCompose:1.1.2")}