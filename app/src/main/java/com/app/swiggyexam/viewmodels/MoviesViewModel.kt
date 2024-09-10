package com.app.swiggyexam.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.swiggyexam.data.model.MockResponseDto
import com.app.swiggyexam.data.repository.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val myRepository: MyRepository
) : ViewModel() {

    private val _movies = MutableLiveData<MockResponseDto.MockResponse>()
    val movies: LiveData<MockResponseDto.MockResponse> = _movies

    private val _movieDetails = MutableLiveData<MockResponseDto.MovieDetailsResponse>()
    val movieDetails: LiveData<MockResponseDto.MovieDetailsResponse> = _movieDetails

    private val _isLoading = MutableLiveData<Boolean>()  // Loading state
    val isLoading: LiveData<Boolean> = _isLoading

    fun fetchMovies(apiKey:String, searchQuery:String) {
        viewModelScope.launch {
            _isLoading.value = true  // Show progress bar
            try {
                _movies.value = myRepository.getMovies(apiKey, searchQuery)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false  // Hide progress bar
            }
        }
    }

    fun fetchMovieDetails(movieId: String, apiKey:String) {
        viewModelScope.launch {
            _isLoading.value = true  // Show progress bar
            try {
                _movieDetails.value = myRepository.getMovieDetails(movieId, apiKey)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false  // Hide progress bar
            }
        }
    }
}