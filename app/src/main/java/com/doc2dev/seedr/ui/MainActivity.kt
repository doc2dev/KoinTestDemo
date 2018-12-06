package com.doc2dev.seedr.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.doc2dev.seedr.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.back_vector)
        navController = Navigation.findNavController(this, R.id.auth_nav_host_fragment)
    }

    fun toggleBackButton(isVisible: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(isVisible)
    }

    override fun onSupportNavigateUp(): Boolean {
        if (navController.currentDestination?.id != R.id.seedListFragment) {
            navController.navigateUp()
        } else {
            finish()
        }
        return true
    }

}
