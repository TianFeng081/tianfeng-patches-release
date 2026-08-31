package dev.ytmcar.patches.music.androidauto

import app.morphe.patcher.patch.bytecodePatch

/**
 * Enables app-side Android Auto browse/search authorization.
 *
 * This does not modify Premium entitlement and does not alter driving-state or
 * distraction-optimization restrictions enforced by Android Auto/the vehicle.
 */
@Suppress("unused")
val ytmCarBrowseSearchPatch = bytecodePatch(
    name = "YTM CAR - Browse and search",
    description = "Enables the CAR build's existing Android Auto browse/search path, including song search where the host exposes it.",
    default = false,
) {
    compatibleWith(YTM_CAR_COMPATIBILITY)

    execute {
        AndroidAutoBrowseSearchAllowlistFingerprint.method.forceBoolean(true)
    }
}
