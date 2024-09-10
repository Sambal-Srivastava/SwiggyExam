package com.app.swiggyexam.network

import com.app.swiggyexam.data.model.MockResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("endpoint1")
    suspend fun getStates(@Query("statename") stateName:String): MockResponseDto.MockResponse
}