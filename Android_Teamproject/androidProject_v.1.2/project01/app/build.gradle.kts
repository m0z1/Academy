plugins {
    id("com.android.application")
    
}

android {
    namespace = "com.findpet.project01"
    compileSdk = 33

    buildFeatures {
        viewBinding = true
    }

    defaultConfig {
        applicationId = "com.findpet.project01"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    useLibrary ("org.apache.http.legacy")
}

dependencies {

    //noinspection GradleCompatible
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation(files("oauth-5.7.0.arr"))
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    // https://mvnrepository.com/artifact/com.github.bumptech.glide/glide
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")

    implementation("com.squareup.okhttp3:okhttp-urlconnection:4.9.0")
    implementation("com.kakao.sdk:v2-all:2.15.0") // 전체 모듈 설치, 2.11.0 버전부터 지원
    implementation("com.kakao.sdk:v2-user:2.15.0") // 카카오 로그인

    implementation("org.jsoup:jsoup:1.13.1")
    implementation ("androidx.recyclerview:recyclerview:1.1.0")






}