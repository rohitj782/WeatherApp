package com.rohitrj.weatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.rohitrj.weatherapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //for navigation graph
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //toolbar
        setSupportActionBar(toolbar)

        //nav graph
        navController = Navigation.findNavController(this,R.id.nav_host_fragment)

        //bottom navigation
        bottom_nav.setupWithNavController(navController)

        //action bar
        NavigationUI.setupActionBarWithNavController(this,navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        //used for back navigation ...back arrows
        return NavigationUI.navigateUp(navController,null)
    }
}
