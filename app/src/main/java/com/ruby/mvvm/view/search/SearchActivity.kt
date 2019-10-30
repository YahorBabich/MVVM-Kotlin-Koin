package com.ruby.mvvm.view.search

import android.os.Bundle
import com.ruby.mvvm.R
import com.ruby.mvvm.view.Arguments.ARG_ADDRESS
import com.ruby.mvvm.view.Arguments.ARG_WEATHER_DATE
import com.ruby.mvvm.view.BaseActivity
import com.ruby.mvvm.view.list.ListActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.koin.android.architecture.ext.viewModel
import java.util.*

class SearchActivity : BaseActivity() {

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
                    displayError(searchEvent.error)
                }
            }
        })
    }

    fun getSearchText() = searchEditText.text.trim().toString()

    fun onWeatherSuccess() {
        startActivity<ListActivity>(
            ARG_WEATHER_DATE to Date(),
            ARG_ADDRESS to getSearchText()
        )
    }
}