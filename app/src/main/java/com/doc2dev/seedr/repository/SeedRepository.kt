package com.doc2dev.seedr.repository

import com.doc2dev.seedr.SeedrApp
import com.doc2dev.seedr.database.entities.SeedEntry
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by Eston on 06/12/2018.
 */
class SeedRepository {

    fun createSeedEntry(seedEntry: SeedEntry): Completable {
        return Completable.create {
            try {
                internalCreateSeedEntry(seedEntry)
                it.onComplete()
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }

    private fun internalCreateSeedEntry(seedEntry: SeedEntry) {
        val dao = SeedrApp.INSTANCE.getDatabase().seedDao()
        dao.addSeedEntry(seedEntry)
    }

    fun getAllSeeds(): Flowable<List<SeedEntry>> {
        val dao = SeedrApp.INSTANCE.getDatabase().seedDao()
        return dao.fetchAllSeeds()
    }
}