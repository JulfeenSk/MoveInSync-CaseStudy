package com.examples.moveinsynccasestudy.recyclerAdapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.examples.moveinsynccasestudy.dataclass.MoviesDataClass
import com.examples.moveinsynccasestudy.R
import com.examples.moveinsynccasestudy.dataclass.GenresDataClass

class GenreRecycler(var cont: Context, var genreItems: GenresDataClass)
    : RecyclerView.Adapter<GenreRecycler.genreHolder>(){

    var genres = genreItems.genres
    var movies  = genreItems.movies

    inner class genreHolder(itemVIew: View) : RecyclerView.ViewHolder(itemVIew)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): genreHolder {
        var layout = LayoutInflater.from(parent.context).inflate(R.layout.genre_item, parent, false)
        return genreHolder(layout)

    }

    override fun getItemCount(): Int {
        return 11;
    }

    override fun onBindViewHolder(holder: genreHolder, position: Int) {
        holder.itemView.apply {



            var genreName = findViewById<TextView>(R.id.genre_text)
            var movieRecycler = findViewById<RecyclerView>(R.id.genre_movies)
            movieRecycler.layoutManager = LinearLayoutManager(cont, LinearLayoutManager.HORIZONTAL , false)


            genreName.text = genres[position];

            var genreWiseList = mutableListOf<List<MoviesDataClass>>()

            genreItems.genres.forEach { genre ->
                val filteredMovies = filterMoviesByGenre(movies, genre)
                genreWiseList.add(filteredMovies)
            }



            movieRecycler.adapter =  MovieRecycler(cont, genreWiseList[position] );
            Log.e("came", "-----> genre")




        }

    }

    fun filterMoviesByGenre(movies: List<MoviesDataClass>, genre: String): List<MoviesDataClass> {
        return movies.filter { movie ->
            movie.genres.contains(genre)
        }
    }
}