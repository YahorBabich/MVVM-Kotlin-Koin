package com.yahorb.mvvm.view.list

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.yahorb.mvvm.R
import com.yahorb.mvvm.extension.dp
import com.yahorb.mvvm.extension.inflater
import com.yahorb.mvvm.model.data.Artist
import kotlinx.android.synthetic.main.item_artist.view.*

class ListAdapter(private val onClick: (Artist) -> Unit) :
    RecyclerView.Adapter<ListAdapter.ArtistHolder>() {

    private var artists: List<Artist> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistHolder {
        val view = parent.inflater(R.layout.item_artist)
        return ArtistHolder(view)
    }

    fun update(artists: List<Artist>) {
        this.artists = artists
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ArtistHolder, position: Int) {
        holder.bind(artists[position])
    }

    override fun getItemCount() = artists.size

    inner class ArtistHolder(private val item: View) :
        RecyclerView.ViewHolder(item) {

        @SuppressLint("SetTextI18n")
        fun bind(artist: Artist) {
            item.apply {
                itemLayout.setOnClickListener { onClick(artist) }
                Glide.with(this).load(artist.artworkUrl100)
                    .transform(RoundedCorners(this.context.dp(R.dimen.roundingRadius)))
                    .into(image)
                artistName.text = artist.artistName
                data.text = "${artist.collectionPrice.toString()} ${artist.currency}"
            }
        }
    }
}