package com.kocci.healtikuy.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.kocci.healtikuy.R
import com.kocci.healtikuy.core.data.remote.model.Async
import com.kocci.healtikuy.databinding.ActivityMainBinding
import com.kocci.healtikuy.util.extension.gone
import com.kocci.healtikuy.util.extension.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //? Sync are done every day (if day has changed -> from lastLogin preferences)
        if (viewModel.isUserLogin) {
            viewModel.syncProgress.observe(this) {
                when (it) {
                    Async.Loading -> {
                        binding.syncIndicator.visible()
                    }

                    is Async.Success -> {
                        binding.syncIndicator.gone()
                    }

                    is Async.Error -> {
                        binding.syncIndicator.gone()
                    }
                }
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