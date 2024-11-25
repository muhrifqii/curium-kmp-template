package com.muhrifqii.core.compose.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.muhrifqii.resources.Res
import com.muhrifqii.resources.ok
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.stringResource

@Composable
expect fun DatePickerDialog(
    onDismissRequest: () -> Unit,
    onDateChanged: (LocalDate) -> Unit,
    selectedDate: LocalDate = remember {
        Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    },
    confirmLabel: String = stringResource(Res.string.ok),
    minimumDate: LocalDate? = null,
    maximumDate: LocalDate? = null,
    title: String = "",
)

@Composable
expect fun TimePickerDialog(
    onDismissRequest: () -> Unit,
    onTimeChanged: (LocalTime) -> Unit,
    selectedTime: LocalTime = remember {
        Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).time
    },
    confirmLabel: String = stringResource(Res.string.ok),
    title: String = "",
)
