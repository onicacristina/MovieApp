package com.example.movieapp.ui.actors

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.ui.genres.Genre
import com.example.movieapp.utils.Constants.IMAGE_URL
import kotlin.coroutines.coroutineContext


class ActorsAdapter(private val actorsList: List<Actor>):
    RecyclerView.Adapter<ActorsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val actorName: TextView = view.findViewById(R.id.tv_name)
        val photoActor :ImageView = view.findViewById(R.id.iv_photo_actor)
        val parentView: ConstraintLayout = view.findViewById(R.id.parent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_actors, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actor = actorsList[position]
        holder.actorName.text = actor.name
        Glide.with(holder.photoActor.context).load(IMAGE_URL + actor.photo).into(holder.photoActor)

        selectActor(holder, actor)

        holder.parentView.setOnClickListener {
            actor.isSelected = !actor.isSelected
            selectActor(holder, actor)
        }

    }

    private fun selectActor(holder: ViewHolder,  actor: Actor){
        holder.parentView.setBackgroundColor(
            when(actor.isSelected){
                true -> ContextCompat.getColor(holder.parentView.context,R.color.gray_100)
                else -> ContextCompat.getColor(holder.parentView.context,R.color.white)
            }
        )
    }

    override fun getItemCount() = actorsList.size


}