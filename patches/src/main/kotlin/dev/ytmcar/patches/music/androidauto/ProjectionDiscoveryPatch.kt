package dev.ytmcar.patches.music.androidauto

import app.morphe.patcher.patch.PatchException
import app.morphe.patcher.patch.resourcePatch
import org.w3c.dom.Element

private const val ANDROID_NS = "http://schemas.android.com/apk/res/android"
private const val AUTOMOTIVE_FEATURE = "android.hardware.type.automotive"
private const val AA_DISCOVERY_META = "com.google.android.gms.car.application"
private const val AAOS_META = "com.android.automotive"

private fun Element.androidAttribute(name: String): String = getAttributeNS(ANDROID_NS, name)

private fun Element.setAndroidAttribute(name: String, value: String) {
    setAttributeNS(ANDROID_NS, "android:$name", value)
}

private fun Element.directChildElements(tagName: String): Sequence<Element> = sequence {
    val nodes = childNodes
    for (index in 0 until nodes.length) {
        val node = nodes.item(index)
        if (node is Element && node.tagName == tagName) yield(node)
    }
}

@Suppress("unused")
val ytmCarProjectionDiscoveryPatch = resourcePatch(
    name = "Specific | YouTube Music CAR | Android Auto projection discovery",
    description = "App-specific patch that makes the CAR_RELEASE build discoverable by phone-side Android Auto using its existing media/template descriptor.",
    default = false,
) {
    compatibleWith(YTM_CAR_COMPATIBILITY)

    execute {
        document("AndroidManifest.xml").use { doc ->
            val manifest = doc.documentElement
            val application = manifest.getElementsByTagName("application").item(0) as? Element
                ?: throw PatchException("Missing application element")

            var automotiveFeatureFound = false
            val features = manifest.getElementsByTagName("uses-feature")
            for (index in 0 until features.length) {
                val feature = features.item(index) as? Element ?: continue
                if (feature.androidAttribute("name") == AUTOMOTIVE_FEATURE) {
                    feature.setAndroidAttribute("required", "false")
                    automotiveFeatureFound = true
                    break
                }
            }

            if (!automotiveFeatureFound) {
                val feature = doc.createElement("uses-feature").apply {
                    setAndroidAttribute("name", AUTOMOTIVE_FEATURE)
                    setAndroidAttribute("required", "false")
                }
                manifest.insertBefore(feature, application)
            }

            val existingDiscovery = application.directChildElements("meta-data")
                .firstOrNull { it.androidAttribute("name") == AA_DISCOVERY_META }
            val aaosMetadata = application.directChildElements("meta-data")
                .firstOrNull { it.androidAttribute("name") == AAOS_META }

            val descriptor = sequenceOf(existingDiscovery, aaosMetadata)
                .filterNotNull()
                .map { it.androidAttribute("resource") }
                .firstOrNull { it.isNotBlank() }
                ?: throw PatchException("Missing Android Auto/Automotive app descriptor metadata")

            if (existingDiscovery != null) {
                existingDiscovery.setAndroidAttribute("resource", descriptor)
            } else {
                val metadata = doc.createElement("meta-data").apply {
                    setAndroidAttribute("name", AA_DISCOVERY_META)
                    setAndroidAttribute("resource", descriptor)
                }
                application.appendChild(metadata)
            }
        }
    }
}
