package com.examples.moveinsynccasestudy.recyclerAdapters


import android.content.Context

import android.content.Intent
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton

import androidx.recyclerview.widget.RecyclerView
import com.examples.moveinsynccasestudy.DetailsActivity
import com.examples.moveinsynccasestudy.dataclass.MoviesDataClass
import com.examples.moveinsynccasestudy.R
import com.squareup.picasso.Picasso


class MovieRecycler(var cont: Context, var movies: List<MoviesDataClass>)
    :RecyclerView.Adapter<MovieRecycler.movieHolder>()
{
    inner class movieHolder(itemVIew: View) : RecyclerView.ViewHolder(itemVIew)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieRecycler.movieHolder {
        var layout = LayoutInflater.from(cont).inflate(R.layout.movie_item, parent, false)
        return movieHolder(layout)

    }

    override fun getItemCount(): Int {
        return 6;
    }

    override fun onBindViewHolder(holder: movieHolder, position: Int) {
        holder.itemView.apply {
            var image = findViewById<ImageButton>(R.id.movie)

//            image.setImageResource(R.drawable.ic_next)

            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            val imageUrl = movies[position].posterUrl

            if( !imageUrl.isBlank()) {

                Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.default_poster)
                    .into(image)
            }else{
                image.setImageResource(R.drawable.default_poster)
            }


            image.setOnClickListener {

                var movieObj = MoviesDataClass(
                    movies[position].actors,
                    movies[position].director,
                    movies[position].genres,
                    movies[position].id,
                    movies[position].plot,
                    movies[position].posterUrl,
                    movies[position].runtime,
                    movies[position].title,
                    movies[position].year,
                )
                var intent = Intent(cont, DetailsActivity::class.java )
                intent.putExtra("movie", movieObj)
                cont.startActivity(intent)



            }

        }
    }

}