package com.doc2dev.seedr

import android.app.Application
import androidx.room.Room
import com.doc2dev.seedr.database.SeedDatabase
import com.doc2dev.seedr.di.appModule
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins
import org.koin.android.ext.android.startKoin
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
        initializeTimber()
        initializeRxErrorHandling()
        startKoin(this, listOf(appModule))
    }

    private fun initializeTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    fun getDatabase(): SeedDatabase {
        return  Room.databaseBuilder(this, SeedDatabase::class.java, "seed-db")
            .fallbackToDestructiveMigration()
            .build()
    }

    private fun initializeRxErrorHandling() {
        RxJavaPlugins.setErrorHandler { e ->
            val cause = if (e is UndeliverableException) e.cause else e
            Timber.d("Undeliverable exception received: %s", cause?.message)
        }
    }
}