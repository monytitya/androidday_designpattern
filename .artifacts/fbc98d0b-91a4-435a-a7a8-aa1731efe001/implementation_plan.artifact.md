# Implementation Plan - Ecommerce App Enhancements

This plan covers integrating image resources, implementing cart quantity logic, and optimizing scroll performance.

## User Review Required

> [!IMPORTANT]
> I will be updating your `CartScreen` to use a mutable list state. This ensures that when you click `+` or `-`, the UI updates instantly and the subtotal/total are recalculated.

## Proposed Changes

### 1. Visual Integration (Images)
- **[MODIFY] [SampleData.kt](file:///C:/Android2026/ANDROIDS26/ANdroidDaythree/app/src/main/java/com/example/androiddaythree/ecommerce/data/SampleData.kt)**: Map `img_1` to `img_6` and `banner` to the product list.
- **[MODIFY] [HomeScreen.kt](file:///C:/Android2026/ANDROIDS26/ANdroidDaythree/app/src/main/java/com/example/androiddaythree/ecommerce/ui/screens/HomeScreen.kt)**: Enable the banner image and set it to `R.drawable.banner`.

### 2. Cart Logic (+/- Buttons)
- **[MODIFY] [CartScreen.kt](file:///C:/Android2026/ANDROIDS26/ANdroidDaythree/app/src/main/java/com/example/androiddaythree/ecommerce/ui/screens/CartScreen.kt)**:
    - Change `cartItems` to use `mutableStateListOf` for reactive updates.
    - Implement `onIncrease` and `onDecrease` functions to update item quantities.
    - Ensure items are removed if quantity reaches zero.

### 3. Performance & Smooth Scrolling
- **[MODIFY] [HomeScreen.kt](file:///C:/Android2026/ANDROIDS26/ANdroidDaythree/app/src/main/java/com/example/androiddaythree/ecommerce/ui/screens/HomeScreen.kt)**:
    - Replace the `Column` + `verticalScroll` with a `LazyColumn` for the main layout. This is essential for smooth scrolling in long lists.
    - Move headers and banners into `item { ... }` blocks within the `LazyColumn`.

## Verification Plan

### Automated Tests
- Run `gradle_sync` to verify resource mapping.
- Run `gradle_build app:assembleDebug`.

### Manual Verification
- **Images**: Verify the banner and product images appear correctly.
- **Cart**: Click `+` and `-` on cart items; verify the quantity changes and the bottom total updates.
- **Scrolling**: Swipe up and down on the Home screen to confirm a buttery-smooth 60fps+ experience.
