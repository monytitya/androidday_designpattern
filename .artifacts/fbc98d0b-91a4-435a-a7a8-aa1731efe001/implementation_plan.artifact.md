# Implementation Plan - Scroll Performance Optimization & Bug Fixes

Identify and resolve performance bottlenecks in the Ecommerce app, specifically focusing on smooth scrolling in `LazyColumn` and `LazyRow` components.

## Proposed Changes

### Performance Optimization

#### [MODIFY] [HomeScreen.kt](file:///C:/Android2026/ANDROIDS26/ANdroidDaythree/app/src/main/java/com/example/androiddaythree/ecommerce/ui/screens/HomeScreen.kt)
- Add unique `key` parameters to all `items(...)` calls in `LazyColumn` and `LazyRow`. This allows Compose to reuse items efficiently instead of re-composing them during scroll.
- Add `contentType` to items to help the list manage recycling more effectively.
- Wrap brand filtering logic and product lists in `remember` or `derivedStateOf` if heavy processing is detected.

#### [MODIFY] [CommonComponents.kt](file:///C:/Android2026/ANDROIDS26/ANdroidDaythree/app/src/main/java/com/example/androiddaythree/ecommerce/ui/components/CommonComponents.kt)
- Optimize `ProductCard` to ensure it is as "skipable" as possible by avoiding unnecessary state changes.
- Ensure `painterResource` usage is efficient (Compose handles caching, but we can verify there are no redundant calls).

#### [MODIFY] [CartScreen.kt](file:///C:/Android2026/ANDROIDS26/ANdroidDaythree/app/src/main/java/com/example/androiddaythree/ecommerce/ui/screens/CartScreen.kt)
- Ensure the `CartItemRow` uses keys correctly (it already does, but I'll double-check for any overhead).

### Bug Fixes
- Double-check for any typos or missing logic in the +/- quantity buttons (already implemented, but will refine for stability).
- Fix the "Prodcuts details" typo in `DetailScreen.kt`.

## Verification Plan

### Automated Tests
- Run `gradle_build` to ensure no regressions.

### Manual Verification
- Use the **Layout Inspector** and **Recomposition Counter** in Android Studio to verify that items are being reused and not re-composed during scroll.
- Perform "fling" gestures on the Home screen to verify buttery-smooth scrolling (aiming for 60/120fps).
