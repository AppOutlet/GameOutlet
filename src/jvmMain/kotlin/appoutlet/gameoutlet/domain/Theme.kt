package appoutlet.gameoutlet.domain

enum class Theme {
    LIGHT, DARK, SYSTEM;

    companion object {
        fun fromString(themeString: String?): Theme {
            return values().firstOrNull { it.name == themeString } ?: SYSTEM
        }
    }
}
