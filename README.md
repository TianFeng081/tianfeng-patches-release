# TianFeng Patches

Personal Morphe patch bundle for supported Android apps.

## Patch naming

Patches are grouped by scope:

- `Specific | <App> | <Feature>` — patches tied to a specific app or app build.
- `Universal | <Feature>` — reusable patches that are not tied to one specific app.

## Specific patches

### YouTube Music CAR_RELEASE

- Package: `com.google.android.apps.youtube.music`
- Version: `9.34.22`
- Tested version code: `93422540`

Included patches:

1. `Specific | YouTube Music CAR | Certificate compatibility`
2. `Specific | YouTube Music CAR | Browse and search`
3. `Specific | YouTube Music CAR | Android Auto projection discovery`
4. `Specific | YouTube Music CAR | Full Android Auto app-side features`

The full patch depends on the other three and enables the app-side Android Auto behavior covered by this bundle.

## Universal patches

No universal patches are included yet.

## Bundle

- Bundle name: `TianFeng Patches`
- Bundle version: `1.0.2`

## Download

Use the `.mpp` asset from the latest GitHub Release, or add this repository as a Remote source in Morphe Manager.

## Scope

This patch set does not patch Premium/subscription entitlement and does not modify Android Auto host/vehicle driving or distraction policy.

This repository contains patch source and build automation only. It does not contain application APK files.
