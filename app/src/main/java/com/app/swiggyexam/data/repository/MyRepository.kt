package com.app.swiggyexam.data.repository

import com.app.swiggyexam.data.model.MockResponseDto
import com.app.swiggyexam.network.ApiService
import javax.inject.Inject

class MyRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getStates(): MockResponseDto.MockResponse {
        return apiService.getStates("")
    }
}