package com.muhrifqii.settings

enum class Theme(val value: String) {
    LIGHT("light"),
    DARK("dark"),
    LIGHT_MEDIUM_CONTRAST("light_medium_contrast"),
    LIGHT_HIGH_CONTRAST("light_high_contrast"),
    DARK_MEDIUM_CONTRAST("dark_medium_contrast"),
    DARK_HIGH_CONTRAST("dark_high_contrast"),
    SYSTEM("system");

    companion object {
        fun fromValue(value: String): Theme {
            return entries.firstOrNull { it.value == value } ?: SYSTEM
        }
    }
}
