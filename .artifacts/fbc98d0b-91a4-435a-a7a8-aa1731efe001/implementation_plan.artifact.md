# Implementation Plan - Fix AndroidX Configuration and Integrate Compose Navigation

The build is currently failing because the `android.useAndroidX` property is missing from `gradle.properties`. Additionally, the `MainActivity` is still using traditional XML layouts while the project is being transitioned to Jetpack Compose with the new `AppNavigation`.

## Proposed Changes

### Build Configuration

#### [MODIFY] [gradle.properties](file:///C:/Android2026/ANDROIDS26/ANdroidDaythree/gradle.properties)
- Add `android.useAndroidX=true` and `android.enableJetifier=true` to enable AndroidX support.

#### [MODIFY] [libs.versions.toml](file:///C:/Android2026/ANDROIDS26/ANdroidDaythree/gradle/libs.versions.toml)
- Add `androidx-activity-compose` to the libraries section.
- Use `2024.12.01` for `composeBom` as seen in the project logs.

#### [MODIFY] [app/build.gradle.kts](file:///C:/Android2026/ANDROIDS26/ANdroidDaythree/app/build.gradle.kts)
- Add `libs.androidx.activity.compose` to dependencies.
- Ensure `libs.kotlinx.serialization.json` is correctly referenced.

### UI Integration

#### [MODIFY] [MainActivity.kt](file:///C:/Android2026/ANDROIDS26/ANdroidDaythree/app/src/main/java/com/example/androiddaythree/MainActivity.kt)
- Change base class to `androidx.activity.ComponentActivity`.
- Replace `setContentView(R.layout.activity_main)` with `setContent { AppNavigation() }`.
- Remove unnecessary View-based window insets logic.

## Verification Plan

### Automated Tests
- Run `gradle_sync` to verify the property changes.
- Run `gradle_build app:assembleDebug` to ensure the project compiles.

### Manual Verification
- Deploy the app to a device/emulator to verify that the `HomeScreen` from `AppNavigation` is displayed and navigation works.
