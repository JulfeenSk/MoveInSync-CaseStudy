package com.examples.moveinsynccasestudy

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.examples.moveinsynccasestudy.API.RetrofitInstance
import com.examples.moveinsynccasestudy.databinding.ActivityMainBinding
import com.examples.moveinsynccasestudy.recyclerAdapters.GenreRecycler
import retrofit2.HttpException
import java.io.IOException


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding;
    private lateinit var recycler: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = findViewById(R.id.tool_bar)
        setSupportActionBar(toolbar)




        var swipeImages = listOf<Int>(
            R.drawable.doomsday,
            R.drawable.secretwars,
            R.drawable.venom,
            R.drawable.pushpa,
            R.drawable.image2
        )

        var swipeViewPage = findViewById<ViewPager2>(R.id.trending)

        var adapoter = SwipeCards(this, swipeImages)
        swipeViewPage.adapter = adapoter



        recycler = findViewById(R.id.genre_recycle)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL , false)

        lifecycleScope.launchWhenCreated {

            val response = try {
                RetrofitInstance.api.getTodos()
            } catch(e: IOException) {
                Log.e("MainActivity", "IOException, you might not have internet connection")

                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e("MainActivity", "HttpException, unexpected response")

                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body() != null) {
                recycler.adapter = GenreRecycler( this@MainActivity, response.body()!!)
                Log.e("came", "-----> main")

            } else {
                Log.e("MainActivity", "Response not successful")
            }

        }
    }

}
