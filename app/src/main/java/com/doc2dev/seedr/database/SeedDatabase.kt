package com.doc2dev.seedr.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.doc2dev.seedr.database.dao.SeedDao
import com.doc2dev.seedr.database.entities.SeedEntry

/**
 * Created by Eston on 06/12/2018.
 */
@Database(entities = [SeedEntry::class], version = 1, exportSchema = false)
abstract class SeedDatabase : RoomDatabase() {
    abstract fun seedDao(): SeedDao
}