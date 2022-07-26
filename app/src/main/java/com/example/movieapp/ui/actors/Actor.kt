package com.example.movieapp.ui.actors

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "actors")
class Actor (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "photo")
    var photo: String,

    @ColumnInfo(name = "isSelected")
    var isSelected: Boolean
)
{
    override fun equals(other: Any?) = (other is Actor) && id ==  other.id

    override fun toString(): String {
        return "Actor(id=$id, name='$name', isSelected=$isSelected)"
    }

}