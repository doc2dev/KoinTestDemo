package com.doc2dev.seedr.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Created by Eston on 06/12/2018.
 */
@Entity(tableName = "seed_entries")
data class SeedEntry (
    @ColumnInfo(name = "brand_name") val brandName: String,
    @ColumnInfo(name = "seed_type") val seedType: String,
    val quantity: Int,
    @ColumnInfo(name = "unique_id") @PrimaryKey val uniqueId: Long,
    @ColumnInfo(name = "phone_number") val customerPhone: String,
    @ColumnInfo(name = "saved_to_remote") var persistedToBackend: Boolean = false,
    @ColumnInfo(name = "date_created") val dateCreated: Date = Date()
)