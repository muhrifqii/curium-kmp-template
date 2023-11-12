package com.muhrifqii.logger.functions

import com.muhrifqii.logger.Logger
import io.github.aakira.napier.Antilog

expect fun crashAntilog(): Antilog

expect fun Logger.setUserId(id: String)
