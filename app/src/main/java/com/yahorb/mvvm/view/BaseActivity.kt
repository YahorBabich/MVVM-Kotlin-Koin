package com.yahorb.mvvm.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.yahorb.mvvm.R

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {
    fun onError(error: Throwable?) {
        Toast.makeText(
            this,
            getString(R.string.error_happened, error.toString()),
            Toast.LENGTH_SHORT
        ).show()
    }
}