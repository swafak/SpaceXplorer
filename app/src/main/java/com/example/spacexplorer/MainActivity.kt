package com.example.spacexplorer

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.spacexplorer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        //enableEdgeToEdge()
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.apply {
            val navHostFragment =
                supportFragmentManager.findFragmentById(binding.fragmentContainer.id) as NavHostFragment
            val navController = navHostFragment.navController

            navView.setupWithNavController(navController)
            val appBarConfiguration = AppBarConfiguration(
                setOf(
                    com.example.features.R.id.navigation_rocket,
                    com.example.features.R.id.navigation_explore
                )
            )
            setupActionBarWithNavController(navController, appBarConfiguration)

            when (navController.currentDestination?.id) {
                com.example.features.R.id.navigation_rocket -> {
                    supportActionBar?.apply {
                        //setHomeButtonEnabled(true)
                        setHomeAsUpIndicator(com.example.resources.R.drawable.baseline_arrow_back_24)
                        setDisplayHomeAsUpEnabled(true)
                    }
                }
            }
        }
    }

    fun hideBottomNavAndToolBar() {
        binding.apply {
            navView.isGone()
            toolbar.isGone()
        }

    }

    fun showBottomNavAndToolBar() {
        binding.apply {
            navView.isVisible()
            toolbar.isVisible()
        }
    }

}