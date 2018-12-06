package com.doc2dev.seedr.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

/**
 * Created by Eston on 06/12/2018.
 */
@Entity(tableName = "seed_entries")
data class SeedEntry (
    @ColumnInfo(name = "brand_name") val brandName: String,
    @ColumnInfo(name = "seed_type") val seedType: String,
    val quantity: Int,
    @ColumnInfo(name = "unique_id") val uniqueId: Long,
    @ColumnInfo(name = "phone_number") val customerPhone: String
)