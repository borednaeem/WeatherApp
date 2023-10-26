package com.example.weatherapp.util

import com.example.weatherapp.util.DateTimeHelper.DEFAULT_DATE_FORMAT
import com.example.weatherapp.util.DateTimeHelper.DEFAULT_TIME_FORMAT
import java.text.SimpleDateFormat
import java.util.*


fun Date.getDayName(): String {
    return format("EEEE")
}

fun Date.getFormattedTime(): String {
    return format(DEFAULT_TIME_FORMAT)
}

fun Date.getFormattedDate(): String {
    return format(DEFAULT_DATE_FORMAT)
}

fun Date.format(format: String): String {
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    return sdf.format(this)
}