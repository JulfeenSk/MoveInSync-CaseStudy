package com.examples.moveinsynccasestudy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class SwipeCards (var context: Context,
                  var list : List<Int>

): RecyclerView.Adapter<SwipeCards.swipeCardsViewHolder>() {

    inner class swipeCardsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): swipeCardsViewHolder {
        var layout = LayoutInflater.from(context).inflate(R.layout.swipe_image, parent, false)
        return swipeCardsViewHolder(layout)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: swipeCardsViewHolder, position: Int) {
        holder.itemView.apply {
            var swipeImage = findViewById<ImageView>(R.id.imgSwipeView)
            swipeImage.setImageResource(list[position])
        }
    }

}