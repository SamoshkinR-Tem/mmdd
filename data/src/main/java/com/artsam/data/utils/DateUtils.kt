package com.artsam.data.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor
import java.util.*

object DateUtils {

    /**
     * Example:
     * 2022-11-21T22:07:42.2017430
     */
    const val STANDARD_SERVER_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS"

    /**
     * Example:
     * 2023-02-27T14:39:02.471Z
     */
    const val DATE_TIME_SHORT_TIME_ZONE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

    /**
     * May 5, 2023 05:35 AM
     */
    const val DATE_TIME_USER_FORMAT = "MMM d, yyyy hh:mm a"

    /**
     * Example:
     * 1/17/2022
     */
    const val MONTH_DAY_YEAR_FORMAT = "MM/dd/yyyy"

    /**
     * Example:
     * May 5, 2023
     */
    const val MONTH_DAY_YEAR_FORMAT_2 = "MMM d, yyyy"

    /**
     * Example:
     * Sunday - 6 November
     */
    const val DAY_WORD_DAY_NUMBER_MONTH_WORD_FORMAT = "EEEE - d MMMM"

    /**
     * Examples:
     * 5:38AM
     * 3:03PM
     */
    const val HOURS_MINUTES_AM_PM_FORMAT = "K:mma"

    /**
     * Examples:
     * 05/23
     */
    const val MONTH_YEAR_FORMAT = "MM/yy"

    /**
     * Examples:
     * 202507
     */
    const val YEAR_MONTH_FORMAT = "yyyyMM"

    fun formatDate(format: String, date: TemporalAccessor): String {
        return DateTimeFormatter.ofPattern(format, Locale.US).format(date)
    }

    /**
     * Apply to [ZonedDateTime] minimum local time of it's day and set time zone to UTC
     */
    fun ZonedDateTime.withTimeAtStartOfDayUtc(): ZonedDateTime {
        return with(LocalTime.MIN).withZoneSameInstant(ZoneOffset.UTC)
    }

    /**
     * Apply to [ZonedDateTime] maximum local time of it's day and set time zone to UTC
     */
    fun ZonedDateTime.withTimeAtEndOfDayUtc(): ZonedDateTime {
        return with(LocalTime.MAX).withZoneSameInstant(ZoneOffset.UTC)
    }

    fun utcToZonedDateTime(timeStampUtc: String?): ZonedDateTime {
        return if (timeStampUtc != null) {
            val formatter = DateTimeFormatter.ofPattern(STANDARD_SERVER_FORMAT, Locale.US)
            val localDateTime = LocalDateTime.parse(timeStampUtc, formatter)
            localDateTime.atZone(ZoneId.of("UTC"))
        } else {
            ZonedDateTime.now()
        }
    }

    fun utcToLocalZonedDateTime(timeStampUtc: String?): ZonedDateTime {
        return if (timeStampUtc != null) {
            val formatter = DateTimeFormatter.ofPattern(STANDARD_SERVER_FORMAT, Locale.US)
            val localDateTime = LocalDateTime.parse(timeStampUtc, formatter)
            localDateTime.atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneOffset.systemDefault())
        } else {
            ZonedDateTime.now()
        }
    }

    fun isToday(date: ZonedDateTime): Boolean {
        return date.toLocalDate().equals(LocalDate.now(date.zone))
    }

    fun isSameDays(beforeDate: ZonedDateTime, afterDate: ZonedDateTime): Boolean {
        return beforeDate.toLocalDate().equals(afterDate.toLocalDate())
    }
}