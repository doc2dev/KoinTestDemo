package com.doc2dev.seedr.viewmodel

import androidx.lifecycle.ViewModel
import com.doc2dev.seedr.database.entities.SeedEntry
import com.doc2dev.seedr.repository.SeedRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Eston on 06/12/2018.
 */
class SeedViewModel(private val repository: SeedRepository) : ViewModel() {

    fun persistSeedEntry(
        brandName: String,
        seedType: String,
        seedQuantity: Int,
        uniqueId: Long,
        customerPhone: String
    ): Completable {
        val seedEntry = SeedEntry(
            brandName,
            seedType,
            seedQuantity,
            uniqueId,
            customerPhone
        )
        return repository.createSeedEntry(seedEntry)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getAllSeeds(): Flowable<List<SeedEntry>> = repository.getAllSeeds()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}