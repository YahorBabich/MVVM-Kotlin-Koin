package com.yahorb.mvvm.repository.data

import com.yahorb.mvvm.repository.data.geocode.Geocode
import com.yahorb.mvvm.repository.data.geocode.Location

fun Geocode.getLocation(): Location? = results.firstOrNull()?.geometry?.location