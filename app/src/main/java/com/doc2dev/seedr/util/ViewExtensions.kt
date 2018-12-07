package com.doc2dev.seedr.util

import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.doc2dev.seedr.R
import com.doc2dev.seedr.rx.LifeCycleDisposable
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import io.reactivex.disposables.Disposable

/**
 * Created by Eston on 06/12/2018.
 */
fun TextInputLayout.text(): String {
    return this.editText?.text.toString().trim()
}

fun View.makeShortSnackbar(message: String) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_SHORT)
    val view = snackbar.view
    view.setBackgroundColor(ContextCompat.getColor(context!!, R.color.colorAccent))
    snackbar.show()
}

fun Fragment.attachToLifecycle(disposable: Disposable) {
    lifecycle.addObserver(LifeCycleDisposable(disposable))
}