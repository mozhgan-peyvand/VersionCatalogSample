package util

object MpVersions {
    private const val versionMajor = 2
    private const val versionMinor = 2
    private const val versionPatch = 7
    private const val versionOffset = 0

    const val versionCode =
        (versionMajor * 10000 + versionMinor * 100 + versionPatch) * 100 + versionOffset
    const val versionName = "$versionMajor.$versionMinor.$versionPatch"

    const val buildTools = "33.0.0"
    const val compileSdk = 33
    const val minSdk = 21
    const val targetSdk = 33
}
