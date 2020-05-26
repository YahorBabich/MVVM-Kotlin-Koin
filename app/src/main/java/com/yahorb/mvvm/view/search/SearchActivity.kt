package com.yahorb.mvvm.view.search

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import com.yahorb.mvvm.BuildConfig
import com.yahorb.mvvm.R
import com.yahorb.mvvm.extension.observe
import com.yahorb.mvvm.view.BaseActivity
import com.yahorb.mvvm.view.list.ListActivity
import com.yahorb.mvvm.view.search.model.SearchModel
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : BaseActivity() {

    private val viewModel: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        search.setOnEditorActionListener { view, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.search("jakson")
            }
            true
        }
        version.text = getString(R.string.app_version, BuildConfig.VERSION_NAME)

        viewModel.apply {
            observe(this.searchEvent, ::display)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.deleteAll()
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

    private fun onSuccess() {
        startActivity<ListActivity>()
    }
}