package dev.ytmcar.patches.music.androidauto

import app.morphe.patcher.patch.bytecodePatch

@Suppress("unused")
val ytmCarFullAndroidAutoPatch = bytecodePatch(
    name = "YTM CAR - Full Android Auto app-side features",
    description = "Applies certificate compatibility, browse/search authorization, and Android Auto projection discovery.",
    default = false,
) {
    compatibleWith(YTM_CAR_COMPATIBILITY)
    dependsOn(
        ytmCarCertificateCompatibilityPatch,
        ytmCarBrowseSearchPatch,
        ytmCarProjectionDiscoveryPatch,
    )
}
