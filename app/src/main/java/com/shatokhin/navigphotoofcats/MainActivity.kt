package com.shatokhin.navigphotoofcats

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.shatokhin.navigphotoofcats.databinding.ActivityMainBinding
import com.shatokhin.navigphotoofcats.presentation.viewmodels.ViewModelMain
import com.shatokhin.navigphotoofcats.presentation.viewmodels.ViewModelMainFactory


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModelMain: ViewModelMain by viewModels { ViewModelMainFactory() }
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initButtonNavigationView()

        viewModelMain.loadRandomCats()

        viewModelMain.errorNetwork.observe(this){ textError ->
            Toast.makeText(this, textError, Toast.LENGTH_SHORT).show()
        }


    }

    private fun initButtonNavigationView() {

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)

    }

}