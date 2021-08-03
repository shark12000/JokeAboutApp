package com.example.jokeaboutapp.data

import android.icu.text.IDNA
import com.example.jokeaboutapp.domain.UserData

class InfoRepositoryImpl(private val infoPreferences: InfoPreferences) : InfoRepository {
    private val api: Api = Api.create()
    private var userData = infoPreferences.loadData()

    override fun saveData(userData: UserData) {
        infoPreferences.saveData(userData)
        this.userData = userData
    }

    override suspend fun loadData(): UserData {
        return userData
    }

    override suspend fun getJoke() : String {
       return api.fetch(userData.firstName, userData.lastName).toString()
    }


}