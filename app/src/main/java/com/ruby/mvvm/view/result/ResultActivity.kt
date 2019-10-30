package com.ruby.mvvm.view.result

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.startActivity
import org.koin.android.architecture.ext.viewModel
import com.ruby.mvvm.R
import com.ruby.mvvm.util.ext.argument
import com.ruby.mvvm.util.ext.withArguments
import com.ruby.mvvm.view.Arguments.ARG_ADDRESS
import com.ruby.mvvm.view.Arguments.ARG_WEATHER_DATE
import com.ruby.mvvm.view.Arguments.ARG_WEATHER_ITEM_ID
import com.ruby.mvvm.view.detail.DetailActivity
import java.util.*

class ResultActivity : AppCompatActivity() {

    val date: Date by argument(ARG_WEATHER_DATE)
    val address: String by argument(ARG_ADDRESS)

    val viewModel: ResultViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        viewModel.selectEvent.observe(this, androidx.lifecycle.Observer {
            if (it != null) {
                if (it.id != null) {
                    onWeatherSelected(it.id)
                } else if (it.error != null) {
                    displayError(it.error)
                }
            }
        })

        // Launch fragments
        val weatherResultTitleFragment = ResultHeaderFragment()
            .withArguments(
                ARG_WEATHER_DATE to date,
                ARG_ADDRESS to address
            )

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.weather_title, weatherResultTitleFragment)
            .commit()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.weather_list, ResultListFragment())
            .commit()

    }

    fun onWeatherSelected(id: String) {
        startActivity<DetailActivity>(
            ARG_WEATHER_DATE to date,
            ARG_ADDRESS to address,
            ARG_WEATHER_ITEM_ID to id
        )
    }

    fun displayError(error: Throwable?) {
        currentFocus?.let { Snackbar.make(it, "Got error : $error", Snackbar.LENGTH_LONG).show() }
    }
}
