package com.example.jokeaboutapp.data

import com.example.jokeaboutapp.domain.UserData
import retrofit2.Call

interface InfoRepository {
    fun saveData(userData: UserData)
    suspend fun loadData(): UserData
    suspend fun getJoke() : String
}