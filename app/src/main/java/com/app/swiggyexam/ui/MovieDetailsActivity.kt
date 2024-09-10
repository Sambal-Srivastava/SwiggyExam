package com.app.swiggyexam.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.swiggyexam.R
import com.app.swiggyexam.databinding.ActivityMainBinding
import com.app.swiggyexam.databinding.ActivityMovieDetailsBinding
import com.app.swiggyexam.utils.Constants
import com.app.swiggyexam.viewmodels.MoviesViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsActivity : AppCompatActivity() {

    private var _binding: ActivityMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private val moviesViewModel: MoviesViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        moviesViewModel.fetchMovieDetails(
            intent.getStringExtra("movieId").toString(), Constants.API_KEY
        )
        moviesViewModel.movieDetails.observe(this) {
            binding.tvMovieName.text = it.title
            Glide.with(this)
                .load(it.poster)
                .into(binding.ivMovie)
        }

        moviesViewModel.isLoading.observe(this){ isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }
    }
}