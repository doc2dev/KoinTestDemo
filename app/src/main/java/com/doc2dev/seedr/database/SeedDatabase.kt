package com.doc2dev.seedr.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.doc2dev.seedr.database.dao.SeedDao
import com.doc2dev.seedr.database.entities.SeedEntry
import com.doc2dev.seedr.database.typeConverters.DateTypeConverter

/**
 * Created by Eston on 06/12/2018.
 */
@Database(entities = [SeedEntry::class], version = 2, exportSchema = false)
@TypeConverters(DateTypeConverter::class)
abstract class SeedDatabase : RoomDatabase() {
    abstract fun seedDao(): SeedDao
}