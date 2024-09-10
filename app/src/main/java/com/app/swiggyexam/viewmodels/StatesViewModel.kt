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
class StatesViewModel @Inject constructor(
    private val myRepository: MyRepository
) : ViewModel() {

    private val _users = MutableLiveData<MockResponseDto.MockResponse>()
    val states: LiveData<MockResponseDto.MockResponse> = _users

    private val _isLoading = MutableLiveData<Boolean>()  // Loading state
    val isLoading: LiveData<Boolean> = _isLoading

    fun fetchStates(useLocalData: Boolean) {
        viewModelScope.launch {
            _isLoading.value = true  // Show progress bar
            try {
                _users.value = myRepository.getStates()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false  // Hide progress bar
            }
        }
    }
}