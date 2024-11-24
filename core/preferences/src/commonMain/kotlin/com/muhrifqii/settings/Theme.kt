package com.muhrifqii.settings

enum class Theme(val value: String) {
    LIGHT("light"),
    DARK("dark"),
    SYSTEM("system");

    companion object {
        fun fromValue(value: String): Theme {
            return entries.firstOrNull { it.value == value } ?: SYSTEM
        }
    }
}
