import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)

    kotlin("plugin.serialization").version("1.9.22")
    id("app.cash.sqldelight") version "2.0.1"
}

repositories {
    google()
    mavenCentral()
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    jvm("desktop")
    
    sourceSets {
        val desktopMain by getting

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.ui)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)
            implementation(libs.voyager.core)       //runtimeOnly not needed anymore?
            implementation(libs.voyager.screenModel)
            implementation(libs.voyager.navigator)
            implementation(libs.voyager.tab.navigator)
            implementation(libs.voyager.transitions)
            implementation(libs.androidx.material.icons.extended)
            ///implementation (libs.maps.compose)
            implementation(libs.androidx.ui.tooling.preview.v154)

            /*                implementation ("com.google.maps.android:maps-compose:4.3.2")

                            // Optionally, you can include the Compose utils library for Clustering,
                            // Street View metadata checks, etc.
                            implementation ("com.google.maps.android:maps-compose-utils:4.3.2")

                            // Optionally, you can include the widgets library for ScaleBar, etc.
                            implementation ("com.google.maps.android:maps-compose-widgets:4.3.2")*/

            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.xml)
            implementation(libs.kotlinx.datetime)
            implementation(libs.ktor.client.logging)
            implementation(libs.kermit)
            implementation("com.prof18.rssparser:rssparser:6.0.6")
            api("io.github.kevinnzou:compose-webview-multiplatform:1.8.8")
            implementation("de.charlex.compose.material3:material3-html-text:2.0.0-beta01")
            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor)
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
        }

        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.ktor.client.android)
            implementation(libs.sqldelight.android.driver)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.koin.android)
            implementation(libs.voyager.koin)
        }

        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.sqldelight.sqlite.driver)
        }

        /*iosMain.dependencies {
            implementation("io.ktor:ktor-client-darwin:$ktorVersion")
            implementation("com.squareup.sqldelight:native-driver:$sqlDelightVersion")
        }*/


    }
}

android {
    namespace = "cz.cvut.fit.nidip.troksfil"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "cz.cvut.fit.nidip.troksfil"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}
dependencies {
    implementation(libs.androidx.material3)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.core)
    commonMainApi(libs.mvvm.core)
    commonMainApi(libs.mvvm.compose)
    commonMainApi(libs.mvvm.flow)
    commonMainApi(libs.mvvm.flow.compose)
}

/*dependencies {
    commonMainApi("dev.icerock.moko:maps:0.6.0")
    commonMainApi("dev.icerock.moko:maps-google:0.6.0")
    commonMainApi("dev.icerock.moko:maps-mapbox:0.6.0")
}

kotlin.targets
    .matching { it is org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget }
    .configureEach {
        val target = this as org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

        target.binaries
            .matching { it is org.jetbrains.kotlin.gradle.plugin.mpp.Framework }
            .configureEach {
                val framework = this as org.jetbrains.kotlin.gradle.plugin.mpp.Framework
                val frameworks = listOf("Base", "Maps").map { frameworkPath ->
                    project.file("../ios-app/Pods/GoogleMaps/$frameworkPath/Frameworks").path.let { "-F$it" }
                }.plus(
                    project.file("../ios-app/Pods/Mapbox-iOS-SDK/dynamic").path.let { "-F$it" }
                )

                framework.linkerOpts(frameworks)
            }
    }*/
compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "cz.cvut.fit.nidip.troksfil"
            packageVersion = "1.0.0"
        }
    }
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("cz.cvut.fit.nidip.troksfil.data.local")
            generateAsync.set(true)
        }
    }
}


// because of expect-actual-classes https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-connect-to-apis.html#expected-and-actual-functions-and-properties
kotlin {
    targets.all {
        compilations.all {
            compilerOptions.configure {
                freeCompilerArgs.add("-Xexpect-actual-classes")
            }
        }
    }
}

