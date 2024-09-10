package com.app.swiggyexam.data.repository

import com.app.swiggyexam.data.model.MockResponseDto
import com.app.swiggyexam.network.ApiService
import javax.inject.Inject

class MyRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getMovies(apiKey:String, searchQuery:String): MockResponseDto.MockResponse {
        return apiService.getMovies(apiKey, searchQuery)
    }

    suspend fun getMovieDetails(movieId: String, apiKey:String): MockResponseDto.MovieDetailsResponse {
        return apiService.getMovieDetails(movieId, apiKey)
    }
}