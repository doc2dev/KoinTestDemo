package com.doc2dev.seedr.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.doc2dev.seedr.database.entities.SeedEntry

/**
 * Created by Eston on 06/12/2018.
 */
@Dao
interface SeedDao {
    @Insert
    fun addSeedEntry(seedEntry: SeedEntry)
}