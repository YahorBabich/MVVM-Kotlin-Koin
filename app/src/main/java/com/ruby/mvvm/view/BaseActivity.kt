package com.ruby.mvvm.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_weather.*

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {
    fun displayError(error: Throwable?) {
        Snackbar.make(weatherList, "Upps...Error has happened : $error", Snackbar.LENGTH_LONG)
            .show()
    }
}