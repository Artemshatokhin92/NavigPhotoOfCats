package com.shatokhin.dbroomphotoofcats

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.shatokhin.dbroomphotoofcats.databinding.ActivityMainBinding
import com.shatokhin.dbroomphotoofcats.presentation.adapters.FragmentPagerAdapter
import com.shatokhin.dbroomphotoofcats.presentation.viewmodels.ViewModelMain
import com.shatokhin.dbroomphotoofcats.presentation.viewmodels.ViewModelMainFactory


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModelMain: ViewModelMain by viewModels { ViewModelMainFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewPager()

        viewModelMain.errorNetwork.observe(this){ textError ->
            Toast.makeText(this, textError, Toast.LENGTH_SHORT).show()
        }

    }

    private fun initViewPager() {
        val pagerAdapter = FragmentPagerAdapter( supportFragmentManager, lifecycle )

        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = pagerAdapter

        // создание TabLayout (view с вкладками) который привязывается к viewPager
        val tabLayout: TabLayout = binding.tabs
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when(position){
                0 -> tab.text = "Votes"
                1 -> tab.text = "Favorites"
            }
        }.attach()


    }

}