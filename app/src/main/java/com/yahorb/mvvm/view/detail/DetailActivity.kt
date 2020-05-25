package com.yahorb.mvvm.view.detail

import android.os.Bundle
import com.bumptech.glide.Glide
import com.yahorb.mvvm.R
import com.yahorb.mvvm.extension.argument
import com.yahorb.mvvm.extension.observe
import com.yahorb.mvvm.model.data.Artist
import com.yahorb.mvvm.view.Arguments.ARG_ARTIST_ITEM_ID
import com.yahorb.mvvm.view.BaseActivity
import com.yahorb.mvvm.view.detail.model.DetailModel
import kotlinx.android.synthetic.main.activity_weather_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : BaseActivity() {
    private val id by argument<Int>(ARG_ARTIST_ITEM_ID)
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_detail)

        viewModel.apply {
            observe(uiData, ::display)
        }
        viewModel.getDetail(id)
    }

    private fun display(model: DetailModel?) {
        model?.apply {
            if (error != null) {
                onError(error)
            }
            artist?.apply {
                displayDetail(this)
            }
        }
    }

    private fun displayDetail(artist: Artist) {
        artist.apply {
            Glide.with(this).load(artist.artworkUrl100).into(image);
        }
    }
}