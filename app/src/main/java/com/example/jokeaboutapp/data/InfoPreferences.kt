package com.example.jokeaboutapp.data

import android.content.Context
import android.content.SharedPreferences
import com.example.jokeaboutapp.domain.UserData

class InfoPreferences(context: Context) {
    private val userPreferences = context.getSharedPreferences("NamePref", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = userPreferences.edit()

    fun saveData(UserData: UserData) {
        editor.putString("name", UserData.firstName)
        editor.putString("lastName", UserData.lastName)
        editor.apply()
    }

    fun loadData(): UserData {
        val firstName = userPreferences.getString("name", "")
        val lastName = userPreferences.getString("lastName", "")
        return UserData(firstName, lastName)
    }
}