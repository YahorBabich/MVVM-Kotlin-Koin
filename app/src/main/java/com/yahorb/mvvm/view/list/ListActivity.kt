package com.yahorb.mvvm.view.list

import android.os.Bundle
import com.yahorb.mvvm.R
import com.yahorb.mvvm.extension.observe
import com.yahorb.mvvm.model.data.Artist
import com.yahorb.mvvm.util.Constants.ARG_ARTIST_ITEM_ID
import com.yahorb.mvvm.view.BaseActivity
import com.yahorb.mvvm.view.detail.DetailActivity
import com.yahorb.mvvm.view.list.model.ListModel
import kotlinx.android.synthetic.main.activity_list.*
import org.jetbrains.anko.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListActivity : BaseActivity() {

    private lateinit var adapter: ListAdapter
    private val viewModel: ListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        viewModel.apply {
            observe(uiData, ::display)
        }

        adapter = ListAdapter(onItemClicked())
        weatherList.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        weatherList.adapter = adapter

        viewModel.getITuneList()
    }

    private fun display(model: ListModel?) {
        model?.apply {
            if (error != null) {
                onError(error)
            }
            artists?.apply {
                displayArtists(this)
            }
        }
    }

    private fun displayArtists(artist: List<Artist>) {
        adapter.update(artist)
    }

    private fun onItemClicked(): (Artist) -> Unit {
        return { artist ->
            startActivity<DetailActivity>(ARG_ARTIST_ITEM_ID to artist.artistId)
        }
    }
}