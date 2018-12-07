package com.doc2dev.seedr.database.typeConverters

import java.util.Date

import androidx.room.TypeConverter

/**
 * Created by Eston on 06/12/2018.
 */
class DateTypeConverter {

    companion object {
        @TypeConverter
        @JvmStatic
        fun toDate(value: Long): Date = Date(value)

        @TypeConverter
        @JvmStatic
        fun toLong(value: Date): Long = value.time
    }
}
