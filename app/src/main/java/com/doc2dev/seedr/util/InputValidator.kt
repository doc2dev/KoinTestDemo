package com.doc2dev.seedr.util


import com.google.android.material.textfield.TextInputLayout

/**
 * Created by Eston on 17/09/2018.
 */

private fun TextInputLayout.validate(isValid: Boolean, errorMessage: String): Boolean {
    if (!isValid) {
        isErrorEnabled = true
        error = errorMessage
        editText!!.requestFocus()
    }
    return isValid
}

fun TextInputLayout.validateNotEmpty(): Boolean {
    error = null
    isErrorEnabled = false
    editText?.let {
        val text = editText!!.text.toString()
        val isValid = !text.isEmpty()
        return validate(isValid, "This field is required.")
    }
    return false
}