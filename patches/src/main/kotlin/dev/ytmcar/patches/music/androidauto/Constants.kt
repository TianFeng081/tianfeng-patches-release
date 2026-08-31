package dev.ytmcar.patches.music.androidauto

import app.morphe.patcher.patch.AppTarget
import app.morphe.patcher.patch.Compatibility

internal val YTM_CAR_COMPATIBILITY = Compatibility(
    name = "YouTube Music CAR",
    packageName = "com.google.android.apps.youtube.music",
    appIconColor = 0xFF0000,
    targets = listOf(
        AppTarget("9.34.22")
    )
)
