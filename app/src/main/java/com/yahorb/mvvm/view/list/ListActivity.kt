package com.yahorb.mvvm.view.list

import android.os.Bundle
import android.view.View
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

        cta.setOnClickListener {
            onBackPressed()
        }

        adapter = ListAdapter(onItemClicked())
        list.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        list.adapter = adapter

        viewModel.apply {
            observe(uiData, ::display)
        }

        viewModel.getITuneList()
    }

    private fun display(model: ListModel?) {
        model?.apply {
            if (error != null) {
                onError(error)
            } else {
                if (artists != null && artists.isNotEmpty()) {
                    displayArtists(artists)
                } else {
                    displayEmptyList()
                }
            }
        }
    }

    private fun displayArtists(artist: List<Artist>) {
        list.visibility = View.VISIBLE
        empty.visibility = View.GONE
        label.visibility = View.GONE
        cta.visibility = View.GONE
        adapter.update(artist)
    }

    private fun displayEmptyList() {
        list.visibility = View.GONE
        empty.visibility = View.VISIBLE
        label.visibility = View.VISIBLE
        cta.visibility = View.VISIBLE
    }

    private fun onItemClicked(): (Artist) -> Unit {
        return { artist ->
            startActivity<DetailActivity>(ARG_ARTIST_ITEM_ID to artist.artistId)
        }
    }
}