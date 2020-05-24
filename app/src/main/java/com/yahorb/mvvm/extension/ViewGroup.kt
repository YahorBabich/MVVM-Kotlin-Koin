package com.yahorb.mvvm.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflater(resource: Int): View {
    return LayoutInflater.from(context).inflate(resource, this, false)
}