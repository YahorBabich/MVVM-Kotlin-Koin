package com.yahorb.mvvm.extension

import android.content.Context
import androidx.annotation.DimenRes

fun Context.px(@DimenRes dimen: Int): Int = resources.getDimension(dimen).toInt()

fun Context.dp(@DimenRes dimen: Int): Int = (px(dimen) / resources.displayMetrics.density).toInt()