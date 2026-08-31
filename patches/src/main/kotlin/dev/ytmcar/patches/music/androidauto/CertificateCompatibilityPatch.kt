package dev.ytmcar.patches.music.androidauto

import app.morphe.patcher.patch.bytecodePatch

/**
 * App-specific certificate compatibility for patched YouTube Music on Android Auto.
 */
@Suppress("unused")
val ytmCarCertificateCompatibilityPatch = bytecodePatch(
    name = "Specific | YouTube Music CAR | Certificate compatibility",
    description = "App-specific patch that allows the patched YouTube Music CAR build to pass its Android Auto certificate checks.",
    default = false,
) {
    compatibleWith(YTM_CAR_COMPATIBILITY)

    execute {
        CheckCertificateFingerprint.method.forceBoolean(true)
        IsGoogleSignedFingerprint.method.forceBoolean(false)
    }
}
