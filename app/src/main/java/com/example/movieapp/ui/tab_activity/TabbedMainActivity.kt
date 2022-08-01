package com.example.movieapp.ui.tab_activity

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.movieapp.databinding.ActivityTabbedMainBinding
import com.example.movieapp.ui.tab_activity.ui.main.SectionsPagerAdapter


class TabbedMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTabbedMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTabbedMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}