package com.susu.susuapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.navHostFragment)

        findViewById<BottomNavigationView>(R.id.bottomNavigationView).setupWithNavController(
            navController = navController
        )
    }

    override fun onSupportNavigateUp() = navController.navigateUp() || super.onSupportNavigateUp()

}