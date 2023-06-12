package com.example.composenotesapp.utils

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {

    @TypeConverter
    fun timeStampFromDate(date: Date):Long{
        return date.time
    }

    @TypeConverter
    fun dateTimestamp(timestamp:Long):Date?{
        return Date(timestamp)
    }
}