package com.example.weatherapp.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateTimeHelper {

    const val DEFAULT_DATE_FORMAT: String = "EEEE, dd MMM yyyy"
    const val DEFAULT_TIME_FORMAT: String = "h:ss a"

    fun dateFromFormat(date: String, format: String): Date? {
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        return try {
            sdf.parse(date)
        } catch (e: ParseException) {
            null
        }
    }
}