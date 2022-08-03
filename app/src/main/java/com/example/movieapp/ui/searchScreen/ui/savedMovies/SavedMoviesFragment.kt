package com.example.movieapp.ui.searchScreen.ui.savedMovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.movieapp.databinding.FragmentSavedMoviesBinding
import com.example.movieapp.ui.tab_activity.ui.main.PlaceholderFragment
import com.example.movieapp.ui.tab_activity.ui.main.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class SavedMoviesFragment : Fragment() {

    private val tabTitles = arrayOf("Favorite", "Watched")

    private var _binding: FragmentSavedMoviesBinding? = null

    private var adapter: AdapterTAbbedPager? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(SavedMoviesViewModel::class.java)

        _binding = FragmentSavedMoviesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager: ViewPager2 = binding.viewPager
        adapter = activity?.let { AdapterTAbbedPager(it) }
        adapter?.addFragment(FavoriteFragment(), tabTitles[0])
        adapter?.addFragment(WatchedFragment(), tabTitles[1])

        viewPager.adapter = adapter
        val tabs: TabLayout = binding.tabs

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = adapter?.getTitle(position)
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}