package com.app.swiggyexam.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.swiggyexam.data.model.MockResponseDto
import com.app.swiggyexam.databinding.ActivityMainBinding
import com.app.swiggyexam.utils.Constants
import com.app.swiggyexam.viewmodels.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MoviesAdapter.OnItemClickListener {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val moviesViewModel: MoviesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMovies.layoutManager = LinearLayoutManager(this)
        binding.rvMovies.setHasFixedSize(true)

        moviesViewModel.fetchMovies(Constants.API_KEY, "marvel")

        moviesViewModel.movies.observe(this) {
            println(it.data)
//            binding.rvMovies.adapter = MoviesAdapter(it.data!!, this)
            val adapter = MoviesAdapter(it.data ?: emptyList(), this, this)
            binding.rvMovies.adapter = adapter
            adapter.notifyDataSetChanged()
        }

        moviesViewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

        binding.mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrBlank()){
                    moviesViewModel.fetchMovies(Constants.API_KEY, "marvel")
                    return false
                }else {
                    moviesViewModel.fetchMovies(Constants.API_KEY, query.toString())
                    return false
                }
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrBlank()){
                    moviesViewModel.fetchMovies(Constants.API_KEY, "marvel")
                    return false
                }else {
                    moviesViewModel.fetchMovies(Constants.API_KEY, newText.toString())
                    return false
                }
            }
        })

    }

    override fun onItemClick(
        position: Int,
        movieId: String
    ) {
        startActivity(Intent(this, MovieDetailsActivity::class.java).putExtra("movieId", movieId))
    }
}