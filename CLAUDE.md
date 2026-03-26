# CLAUDE.md — Project Rules

## Efficiency Rules (MANDATORY)

- **DO NOT explore the project.** The full structure map is below. Use it instead of ls/find/cat.
- **Batch writes:** Plan ALL changes to a file, then write the ENTIRE file in ONE Write command.
- **No re-reading:** After you write a file, do NOT read it back to "verify". Trust your output.
- **Silent execution:** Do not explain steps. Just execute.
- **Parallel downloads:** Batch all curl/wget image downloads into one shell script and run it once.
- **Write, don't edit:** Use Write (full file replacement), not Edit (partial patches).
- **No verification loops:** Do not grep for "TODO" or "placeholder" after writing.

## Content Rules

- ALL text in English — UI, strings.xml, comments, identifiers, logs, README
- No Lorem ipsum, no placeholders — real meaningful content only
- Stock images from Unsplash/Pexels only (not AI-generated)

## Project Structure (DO NOT explore — use this map)

Package path after scaffold: dianichconsult/service/app

### Files to MODIFY (⚡):
- `data/repository/ServiceRepository.kt` — empty services list → fill with real ServiceModel objects
- `data/model/ServiceModel.kt` — data class → verify fields match your content
- `ui/composable/screen/home/HomeScreen.kt` — home layout → hero carousel, service categories, cards
- `ui/composable/screen/onboarding/OnboardingScreen.kt` — 3 slides → fill content
- `ui/composable/screen/splash/SplashScreen.kt` — splash → gradient + logo animation
- `ui/composable/screen/servicedetails/ServiceDetailsScreen.kt` — detail view → polish
- `ui/composable/screen/bookings/BookingsScreen.kt` — bookings list → polish
- `ui/composable/screen/checkout/CheckoutScreen.kt` — booking form → polish
- `ui/composable/screen/settings/SettingsScreen.kt` — settings → polish
- `ui/theme/Color.kt` — brand colors
- `ui/theme/Theme.kt` — rename Skeleton refs, apply brand
- `ui/theme/Type.kt` — typography
- `ServiceApp.kt` — Application class → rename to ANCSLApp
- `res/values/strings.xml` — app_name and UI strings
- `res/drawable/` — stock images

### Files to NOT modify:
- `MainActivity.kt`
- `data/dao/BookingDao.kt` — Room DAO
- `data/database/ANCSLDatabase.kt` — Room DB
- `data/database/converter/Converters.kt`
- `data/datastore/ANCSLOnboardingPrefs.kt`
- `data/entity/BookingEntity.kt`
- `data/repository/BookingRepository.kt`
- `data/repository/ANCSLOnboardingRepo.kt`
- `di/*` — Koin DI modules
- `ui/composable/approot/*`
- `ui/composable/navigation/*`
- `ui/composable/screen/checkout/CheckoutDialog.kt`
- `ui/composable/shared/*` — ANCSLContentWrapper, ANCSLEmptyView
- `ui/state/*`
- `ui/viewmodel/*`
