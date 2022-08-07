package com.myapps.coinksimpleregistersimulator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.myapps.coinksimpleregistersimulator.R
import com.myapps.coinksimpleregistersimulator.databinding.ActivityMainBinding
import com.myapps.coinksimpleregistersimulator.domain.utils.hideToolbar
import com.myapps.coinksimpleregistersimulator.domain.utils.showToolbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var  navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        val appBarConfiguration = AppBarConfiguration(
            setOf()
        )

        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.entryFragment -> {
                    binding.toolbar.hideToolbar()
                }
                R.id.phoneNumberFragment -> {
                    binding.toolbar.showToolbar()
                    binding.tvToolbar.text = getString(R.string.phone_fargment_tb_text)
                }
                R.id.userInfoFragment -> {
                    binding.toolbar.showToolbar()
                    binding.tvToolbar.text = getString(R.string.user_info_tb_text)
                }
                R.id.contractFragment -> {
                    binding.toolbar.showToolbar()
                    binding.tvToolbar.text = getString(R.string.contract_fragment_tb_text)
                }

                else -> {
                    binding.toolbar.showToolbar()
                }
            }
        }

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    }

    override fun onResume() {
        handleBackPress()
        super.onResume()
    }

    private fun handleBackPress() = this.onBackPressedDispatcher.addCallback(object :
        OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            navController.currentDestination?.let { current ->
                when (current.id) {
                    R.id.entryFragment -> finish()
                    else -> navController.navigateUp()
                }
            }
        }
    })

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}