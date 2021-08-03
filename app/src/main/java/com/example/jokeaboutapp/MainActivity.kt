package com.example.jokeaboutapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.jokeaboutapp.data.InfoPreferences
import com.example.jokeaboutapp.data.InfoRepositoryImpl
import com.example.jokeaboutapp.domain.UserData
import com.example.jokeaboutapp.view.MyViewModel
import com.example.jokeaboutapp.view.MyViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var button: Button

    private lateinit var viewModel: MyViewModel
    private lateinit var myViewModelFactory: MyViewModelFactory
    private lateinit var userData: UserData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myViewModelFactory = MyViewModelFactory(repository = InfoRepositoryImpl(infoPreferences = InfoPreferences(context = this)))

        viewModel = ViewModelProvider(this, myViewModelFactory).get(MyViewModel::class.java)

        viewModel.loadData()

        viewModel.userInfoLiveData.observe(this, Observer {

        })

        initViews()

        button.setOnClickListener {
            viewModel.save(
                UserData(
                    firstNameEditText.text.toString(),
                    lastNameEditText.text.toString()
                )
            )
        }

    }


    private fun initViews() {
        firstNameEditText = findViewById(R.id.editText)
        lastNameEditText = findViewById(R.id.editText2)
        button = findViewById(R.id.button)
    }
}