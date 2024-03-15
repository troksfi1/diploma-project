package cz.cvut.fit.nidip.troksfil.domain.util

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDateTime
import java.time.DayOfWeek.FRIDAY
import java.time.DayOfWeek.MONDAY
import java.time.DayOfWeek.SATURDAY
import java.time.DayOfWeek.SUNDAY
import java.time.DayOfWeek.THURSDAY
import java.time.DayOfWeek.TUESDAY
import java.time.DayOfWeek.WEDNESDAY

object DateTimeUtil {
    fun toReadableFormat(dateTime: LocalDateTime): String {
        return getCsDayOfWeekShortcut(DayOfWeek(dateTime.date.dayOfWeek.value)) + " " +
                dateTime.date.dayOfMonth.toString() + ". " +
                dateTime.date.month.value.toString() + ". " +
                dateTime.date.year.toString()
    }

    private fun getCsDayOfWeekShortcut(dayOfWeek: DayOfWeek): String {
        return when (dayOfWeek) {
            MONDAY -> "Po"
            TUESDAY -> "Út"
            WEDNESDAY -> "St"
            THURSDAY -> "Čt"
            FRIDAY -> "Pá"
            SATURDAY -> "So"
            SUNDAY -> "Ne"
        }
    }
}