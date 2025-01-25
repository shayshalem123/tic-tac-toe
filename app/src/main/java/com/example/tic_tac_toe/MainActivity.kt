package com.example.tic_tac_toe

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.tic_tac_toe.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    private lateinit var dataBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        dataBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(dataBinding.root)
    }
}