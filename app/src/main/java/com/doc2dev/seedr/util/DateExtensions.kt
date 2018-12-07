package com.doc2dev.seedr.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Eston on 06/12/2018.
 */

@SuppressLint("SimpleDateFormat")
fun Date.localString(): String = SimpleDateFormat("dd MMM, yyyy 'at' hh:mm").format(this)