package com.yahorb.mvvm.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {
    fun onError(error: Throwable?) {
        Toast.makeText(this, "Upps...Error has happened : $error", Toast.LENGTH_SHORT).show()
    }
}