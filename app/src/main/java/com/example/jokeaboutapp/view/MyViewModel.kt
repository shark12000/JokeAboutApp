package com.example.jokeaboutapp.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import com.example.jokeaboutapp.data.InfoRepository
import com.example.jokeaboutapp.domain.UserData
import kotlinx.coroutines.launch

class MyViewModel(private val repository: InfoRepository) : ViewModel() {

    private val mutableUserInfoLiveData = MutableLiveData<UserData>()
    val userInfoLiveData: LiveData<UserData> get() = mutableUserInfoLiveData



    private val mutableJokeLiveData = MutableLiveData<String>()
    val jokeLiveData: LiveData<String> get() = mutableJokeLiveData

    fun save(userData: UserData) {
        viewModelScope.launch {
            repository.saveData(userData)
        }

    }

     fun loadData() {
        viewModelScope.launch {
            mutableUserInfoLiveData.value = repository.loadData()
        }

    }

    fun getJoke() {
        viewModelScope.launch {
            mutableJokeLiveData.value = repository.getJoke()
        }
    }
}