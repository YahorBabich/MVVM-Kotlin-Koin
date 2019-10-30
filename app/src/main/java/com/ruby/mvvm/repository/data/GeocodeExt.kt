package com.ruby.mvvm.repository.data

import com.ruby.mvvm.repository.data.geocode.Geocode
import com.ruby.mvvm.repository.data.geocode.Location

fun Geocode.getLocation(): Location? = results.firstOrNull()?.geometry?.location