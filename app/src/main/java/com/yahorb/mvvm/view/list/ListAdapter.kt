package com.yahorb.mvvm.view.list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yahorb.mvvm.R
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

        fun bind(artist: Artist) {
            item.weatherItemLayout.setOnClickListener { onClick(artist) }
            item.weatherItemForecast.text = artist.artistName
            item.weatherItemTemp.text = artist.artistId.toString()
        }
    }
}