# Implementation Plan - Ecommerce App with Advanced Animations

Build a full-featured ecommerce app demo based on the provided design, utilizing Jetpack Compose's latest animation capabilities, specifically Shared Element Transitions.

## User Review Required

> [!IMPORTANT]
> I will be using `SharedTransitionLayout` which is part of Compose 1.7.0+. Your project is already configured with Compose BOM 2024.12.01, so it is compatible. I will create a new package `com.example.androiddaythree.ecommerce` to keep this clean.

## Proposed Changes

### Data & Models
- **[NEW] Product.kt**: Data model for shoes (id, name, price, description, imageRes).
- **[NEW] SampleData.kt**: Pre-populated list of shoe products.

### Theme
- **[NEW] EcommerceTheme.kt**: Custom Material3 theme with yellow accent (`0xFFFFD700`) and modern typography.

### Components
- **[NEW] ProductCard.kt**: The source for shared element transitions.
- **[NEW] CategoryChip.kt**: Interactive chips for brand filtering.
- **[NEW] CartItem.kt**: List item for the cart with quantity controls.

### Screens
- **[NEW] HomeScreen.kt**: Features top bar, sale banner, and horizontal/vertical product lists.
- **[NEW] DetailScreen.kt**: Target for shared element transition, featuring product details and size selection.
- **[NEW] CartScreen.kt**: Displays items with animated removal.

### Navigation
- **[NEW] EcommerceNavigation.kt**: Type-safe navigation setup using `SharedTransitionLayout` context.

### Main Integration
- **[MODIFY] MainActivity.kt**: Set the `EcommerceApp` as the main content.

## Verification Plan

### Automated Tests
- Run `gradle_sync` to ensure all imports are valid.
- Run `gradle_build app:assembleDebug` to verify compilation.

### Manual Verification
- Verify that clicking a product on the Home screen triggers a smooth **Shared Element Transition** to the Detail screen.
- Verify that removing an item from the cart uses **AnimatedVisibility** for a smooth exit.
- Verify the **Bottom Navigation** correctly switches between screens.
