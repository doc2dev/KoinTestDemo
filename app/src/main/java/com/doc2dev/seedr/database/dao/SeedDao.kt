package com.doc2dev.seedr.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.doc2dev.seedr.database.entities.SeedEntry
import io.reactivex.Flowable

/**
 * Created by Eston on 06/12/2018.
 */
@Dao
interface SeedDao {
    @Insert(onConflict = REPLACE)
    fun addSeedEntry(seedEntry: SeedEntry)

    @Query("select * from seed_entries")
    fun fetchAllSeeds(): Flowable<List<SeedEntry>>
}