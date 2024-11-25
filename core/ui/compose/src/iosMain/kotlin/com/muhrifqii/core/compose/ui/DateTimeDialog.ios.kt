package com.muhrifqii.core.compose.ui

import androidx.compose.runtime.Composable
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

@Composable
actual fun DatePickerDialog(
    onDismissRequest: () -> Unit,
    onDateChanged: (LocalDate) -> Unit,
    selectedDate: LocalDate,
    confirmLabel: String,
    minimumDate: LocalDate?,
    maximumDate: LocalDate?,
    title: String
) {
    // todo: ios
}

@Composable
actual fun TimePickerDialog(
    onDismissRequest: () -> Unit,
    onTimeChanged: (LocalTime) -> Unit,
    selectedTime: LocalTime,
    confirmLabel: String,
    title: String
) {
    // todo: ios
}
