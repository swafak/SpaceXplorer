package com.example.spacexplorer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.spacexplorer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

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
                    com.example.features.R.id.navigation_explore,
                    com.example.features.R.id.navigation_dragons,
                    com.example.features.R.id.navigation_favorites,
                    com.example.features.R.id.navigation_ships,
                )
            )
            setupActionBarWithNavController(navController, appBarConfiguration)

            when (navController.currentDestination?.id) {
                com.example.features.R.id.navigation_company -> {
                    supportActionBar?.apply {
                        navView.isGone()
                    }
                }
            }
        }

    }
    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.fragmentContainer.id) as NavHostFragment
        val navController = navHostFragment.navController
        return navController.navigateUp() || super.onSupportNavigateUp()
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