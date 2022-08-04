package com.example.movieapp.database.conevrtors

import androidx.room.TypeConverter
import com.example.movieapp.ui.genres.Genre
import com.example.movieapp.ui.movieDetails.Video
import com.example.movieapp.ui.movieDetails.VideoListResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class VideoListConvertor {
    @TypeConverter
    fun toEnum(data: String?) : List<Video?>?{
        if(data == null){
            return Collections.emptyList()
        }
        val itemType = object: TypeToken<List<Video>?>() {}.type
        return Gson().fromJson(data, itemType)
    }

    @TypeConverter
    fun fromEnum(someObjects: List<Video?>?): String?{
        return someObjects?.let { Gson().toJson(someObjects) }
    }
}