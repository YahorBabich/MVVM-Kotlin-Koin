package com.ruby.mvvm.view.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.ruby.mvvm.R
import com.ruby.mvvm.view.Arguments.ARG_ADDRESS
import com.ruby.mvvm.view.Arguments.ARG_WEATHER_DATE
import com.ruby.mvvm.view.result.ResultActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.koin.android.architecture.ext.viewModel
import java.util.*

class SearchActivity : AppCompatActivity() {

    val viewModel: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchButton.setOnClickListener {
            viewModel.searchWeather(getSearchText())

        }

        viewModel.searchEvent.observe(this, androidx.lifecycle.Observer { searchEvent ->
            if (searchEvent != null) {
                if (searchEvent.isSuccess) {
                    onWeatherSuccess()
                } else if (searchEvent.error != null) {
                    onWeatherFailed(searchEvent.error)
                }
            }
        })

        viewModel.uiData.observe(this, androidx.lifecycle.Observer { uiData ->
            if (uiData != null) {
                val searchText = uiData.searchText
                if (searchText != null) {
                    searchEditText.setText(searchText)
                }
            }
        })
    }

    fun getSearchText() = searchEditText.text.trim().toString()

    fun onWeatherSuccess() {
        startActivity<ResultActivity>(
            ARG_WEATHER_DATE to Date(),
            ARG_ADDRESS to getSearchText()
        )
    }

    fun onWeatherFailed(error: Throwable?) {
        Snackbar.make(searchLayout, "Got error : $error", Snackbar.LENGTH_LONG).show()
    }
}