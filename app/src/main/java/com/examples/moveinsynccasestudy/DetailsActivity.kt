package com.examples.moveinsynccasestudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.examples.moveinsynccasestudy.databinding.ActivityDetailsBinding
import com.examples.moveinsynccasestudy.dataclass.MoviesDataClass
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = intent.getSerializableExtra("movie") as? MoviesDataClass

        if (movie != null) {
            if (!movie.posterUrl.isBlank()) {
                Picasso.get().load(movie.posterUrl).placeholder(R.drawable.default_poster).into(binding.poster)
            } else {
                binding.poster.setImageResource(R.drawable.default_poster)
            }

            binding.title.text = movie.title
            binding.description.text = movie.plot
            binding.year.text = movie.year
            binding.runTime.text = movie.runtime
            binding.genre.text = movie.genres.toString()
            binding.director.text = movie.director.replace(", ", "\n")
            binding.actors.text = movie.actors.replace(", ", "\n")

            // Initially hide the description
            binding.description.visibility = android.view.View.GONE

            // Add click listener to toggle visibility of the description
            binding.nextIcon.setOnClickListener {
                if (binding.description.visibility == android.view.View.GONE) {
                    binding.description.visibility = android.view.View.VISIBLE
                } else {
                    binding.description.visibility = android.view.View.GONE
                }
            }
        }
    }
}