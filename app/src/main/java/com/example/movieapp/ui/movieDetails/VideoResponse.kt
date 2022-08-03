package com.example.movieapp.ui.movieDetails

import com.google.gson.annotations.SerializedName

class VideoResponse (
    @SerializedName("id")
    var id: Int,
    @SerializedName("key")
    var key: String,
    @SerializedName("type")
    var type: String
)