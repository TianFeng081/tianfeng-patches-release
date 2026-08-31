package dev.ytmcar.patches.music.androidauto

import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.util.proxy.mutableTypes.MutableMethod

internal fun MutableMethod.forceBoolean(value: Boolean) {
    val literal = if (value) "0x1" else "0x0"
    addInstructions(
        0,
        """
            const/4 v0, $literal
            return v0
        """.trimIndent()
    )
}
