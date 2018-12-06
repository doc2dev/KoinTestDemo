package com.doc2dev.seedr

import android.app.Application
import androidx.room.Room
import com.doc2dev.seedr.database.SeedDatabase
import timber.log.Timber

/**
 * Created by Eston on 06/12/2018.
 */
class SeedrApp: Application() {
    companion object {
        @JvmStatic
        lateinit var INSTANCE: SeedrApp
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    fun getDatabase(): SeedDatabase {
        return  Room.databaseBuilder(this, SeedDatabase::class.java, "seed-db")
            .fallbackToDestructiveMigration()
            .build()
    }
}