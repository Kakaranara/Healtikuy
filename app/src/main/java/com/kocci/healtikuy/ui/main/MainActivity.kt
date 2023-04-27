package com.kocci.healtikuy.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.kocci.healtikuy.R
import com.kocci.healtikuy.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //? Sync are done every day (if day has changed -> from lastLogin preferences)
        runBlocking {
            if (viewModel.isUserLogin) {
                viewModel.syncData()
            }
        }

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        binding.navigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, dest, _ ->
            if (dest.id == R.id.loginFragment) {
                viewModel.logout()
            }
        }


    }

}