package com.yahorb.mvvm.view.search

import android.os.Bundle
import com.yahorb.mvvm.R
import com.yahorb.mvvm.extension.observe
import com.yahorb.mvvm.view.BaseActivity
import com.yahorb.mvvm.view.list.ListActivity
import com.yahorb.mvvm.view.search.model.SearchModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : BaseActivity() {

    private val viewModel: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchButton.setOnClickListener {
            viewModel.search(getSearchText())
        }

        viewModel.apply {
            observe(this.searchEvent, ::display)
        }
    }

    private fun display(model: SearchModel?) {
        model?.apply {
            if (isSuccess) {
                onSuccess()
            } else if (error != null) {
                onError(error)
            }
        }
    }

    private fun getSearchText() = searchEditText.text.trim().toString()

    private fun onSuccess() {
        startActivity<ListActivity>()
    }
}