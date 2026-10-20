import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.FileInputStream
import java.util.Properties

val apikeyPropertiesFile = rootProject.file("apikey.properties")
val apikeyProperties = Properties().apply {
    if (apikeyPropertiesFile.exists()) {
        load(FileInputStream(apikeyPropertiesFile))
    }
}

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "id.ac.polbeng.depandienda.githubprofile"
    compileSdk = 37

    defaultConfig {
        applicationId = "id.ac.polbeng.depandienda.githubprofile"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "ACCESS_TOKEN", apikeyProperties.getProperty("ACCESS_TOKEN", "\"\""))

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

tasks.withType<KotlinCompile> {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_11)
    }
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)

    // library untuk menampilkan gambar bergerak splashscreen
    implementation("com.airbnb.android:lottie:6.3.0")
    // library untuk menampilkan gambar bulat (circle)
    implementation("de.hdodenhof:circleimageview:3.1.0")
    // library untuk menampilkan gambar melalui url
    implementation("com.github.bumptech.glide:glide:4.13.2")
    // library untuk request API
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    // library untuk logging hasil request API
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.9")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}
