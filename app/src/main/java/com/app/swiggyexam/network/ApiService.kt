package com.app.swiggyexam.network

import com.app.swiggyexam.data.model.MockResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(".")
    suspend fun getMovies(@Query("apikey") apiKey:String,
                          @Query("s") searchQuery:String): MockResponseDto.MockResponse

    @GET(".")
    suspend fun getMovieDetails(@Query("i") movieId:String,
                          @Query("apikey") apiKey:String): MockResponseDto.MovieDetailsResponse
}