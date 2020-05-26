package com.yahorb.mvvm.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Artist(
    @field:PrimaryKey
    val artistId: Int,
    val wrapperType: String? = null,
    val kind: String? = null,
    val collectionId: Int? = null,
    val trackId: Int? = null,
    val artistName: String? = null,
    val collectionName: String? = null,
    val trackName: String? = null,
    val collectionCensoredName: String? = null,
    val trackCensoredName: String? = null,
    val collectionArtistName: String? = null,
    val artistViewUrl: String? = null,
    val collectionViewUrl: String? = null,
    val trackViewUrl: String? = null,
    val previewUrl: String? = null,
    val artworkUrl30: String? = null,
    val artworkUrl60: String? = null,
    val artworkUrl100: String? = null,
    val collectionPrice: Double? = null,
    val trackPrice: Double? = null,
    val releaseDate: String? = null,
    val collectionExplicitness: String? = null,
    val trackExplicitness: String? = null,
    val discCount: Int? = null,
    val discNumber: Int? = null,
    val trackCount: Int? = null,
    val trackNumber: Int? = null,
    val trackTimeMillis: Int? = null,
    val country: String? = null,
    val currency: String? = null,
    val primaryGenreName: String? = null,
    val isStreamable: Boolean? = null
)