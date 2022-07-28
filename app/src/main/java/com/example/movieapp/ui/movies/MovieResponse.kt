package com.example.movieapp.ui.movies

import android.icu.text.CaseMap
import android.provider.MediaStore
import com.google.gson.annotations.SerializedName

class MovieResponse (
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("adult")
    var adult: Boolean,
    @SerializedName("poster_path")
    var poster_path: String,
    @SerializedName("overview")
    var overview: String,
    @SerializedName("backdrop_path")
    var backdrop_path: String,
    @SerializedName("release_date")
    var release_date: String

    )