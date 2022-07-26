package com.example.movieapp.database.conevrtors

import androidx.room.TypeConverter
import com.example.movieapp.ui.movieDetails.Video
import com.example.movieapp.ui.movieDetails.VideoListResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class VideoConvertor {

    @TypeConverter
    fun toEnum(data: String?) : VideoListResponse?{
        if(data == null){
            return null
        }
        val itemType = object: TypeToken<VideoListResponse?>() {}.type
        return Gson().fromJson(data, itemType)
    }

    @TypeConverter
    fun fromEnum(someObjects: VideoListResponse?): String?{
        return someObjects?.let { Gson().toJson(someObjects) }
    }
}