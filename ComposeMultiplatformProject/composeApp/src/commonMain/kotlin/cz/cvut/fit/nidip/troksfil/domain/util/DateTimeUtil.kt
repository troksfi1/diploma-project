package cz.cvut.fit.nidip.troksfil.domain.util

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDateTime

object DateTimeUtil {
    fun toReadableFormat(dateTime: LocalDateTime): String {
        return getCsDayOfWeekShortcut(dateTime.date.dayOfWeek) + " " +
                dateTime.date.dayOfMonth.toString() + ". " +
                dateTime.date.monthNumber.toString() + ". " +
                dateTime.date.year.toString()
    }

    private fun getCsDayOfWeekShortcut(dayOfWeek: DayOfWeek): String {
        return when (dayOfWeek) {
            DayOfWeek.MONDAY -> "Po"
            DayOfWeek.TUESDAY -> "Út"
            DayOfWeek.WEDNESDAY -> "St"
            DayOfWeek.THURSDAY -> "Čt"
            DayOfWeek.FRIDAY -> "Pá"
            DayOfWeek.SATURDAY -> "So"
            DayOfWeek.SUNDAY -> "Ne"
            else -> TODO()
        }
    }
}