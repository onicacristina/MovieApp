package com.example.movieapp.database.conevrtors

import androidx.room.TypeConverter
import com.example.movieapp.ui.genres.Genre
import com.example.movieapp.ui.movieDetails.Video
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class GenreConvertor {

    @TypeConverter
    fun toEnum(data: String?) : List<Genre?>?{
        if(data == null){
            return Collections.emptyList()
        }
        val itemType = object: TypeToken<List<Genre>?>() {}.type
        return Gson().fromJson(data, itemType)
    }

    @TypeConverter
    fun fromEnum(someObjects: List<Genre?>?): String?{
        return someObjects?.let { Gson().toJson(someObjects) }
    }
}