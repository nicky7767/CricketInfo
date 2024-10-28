package com.vinay.cricin.utils

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date

fun FormatDate(gmtDateString: String): String {

    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    inputFormat.timeZone = java.util.TimeZone.getTimeZone("GMT") // Set the time zone to GMT

    val outputFormat = SimpleDateFormat("dd MMM", Locale.getDefault())

    val date: Date = inputFormat.parse(gmtDateString) ?: return "" // Return empty string if parsing fails
    return outputFormat.format(date)
}