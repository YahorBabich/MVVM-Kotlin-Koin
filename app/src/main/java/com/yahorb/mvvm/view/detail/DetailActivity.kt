package com.yahorb.mvvm.view.detail

import android.annotation.SuppressLint
import android.os.Bundle
import com.bumptech.glide.Glide
import com.yahorb.mvvm.R
import com.yahorb.mvvm.extension.argument
import com.yahorb.mvvm.extension.observe
import com.yahorb.mvvm.model.data.Artist
import com.yahorb.mvvm.util.Constants.ARG_ARTIST_ITEM_ID
import com.yahorb.mvvm.util.Constants.DATA_TEMPLATE_FULL
import com.yahorb.mvvm.util.Constants.DATA_TEMPLATE_SHORT
import com.yahorb.mvvm.view.BaseActivity
import com.yahorb.mvvm.view.detail.model.DetailModel
import kotlinx.android.synthetic.main.activity_artist_detail.*
import kotlinx.android.synthetic.main.layout_artist_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : BaseActivity() {
    private val id by argument<Int>(ARG_ARTIST_ITEM_ID)
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_detail)

        setSupportActionBar(toolbar);
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

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

    @SuppressLint("SetTextI18n")
    private fun displayDetail(artist: Artist) {
        artist.apply {
            Glide.with(this@DetailActivity).load(artworkUrl100).into(image);
            collapsingToolbarLayout.title = artist.artistName
            collectionArtistValue.text = collectionArtistName
            censoredValue.text = collectionCensoredName
            collectionExplicitnessValue.text = collectionExplicitness
            collectionValue.text = collectionName
            collectionPriceValue.text = "${collectionPrice.toString()} $currency"
            countryValue.text = country

            releaseDate?.apply {
                val formatter = SimpleDateFormat(DATA_TEMPLATE_FULL, Locale.ENGLISH)
                formatter.parse(releaseDate)?.apply {
                    releaseDateValue.text =
                        SimpleDateFormat(DATA_TEMPLATE_SHORT, Locale.ENGLISH).format(this)
                }
            }
        }
    }
}