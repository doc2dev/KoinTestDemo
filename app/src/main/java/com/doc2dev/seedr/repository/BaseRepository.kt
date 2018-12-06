package com.doc2dev.seedr.repository

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Eston on 06/12/2018.
 */
abstract class BaseRepository {
    protected fun <T> convertToSingle(observable: Observable<T>): Single<T> {
        return Single.fromObservable(
            observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        )
    }
}