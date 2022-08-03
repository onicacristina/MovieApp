package com.example.movieapp.ui.searchScreen.ui.savedMovies

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class AdapterTAbbedPager(activity: FragmentActivity):
    FragmentStateAdapter(activity) {

    private val mFragmentList: MutableList<Fragment> = mutableListOf()
    private val mFragmentTitleList: MutableList<String> = mutableListOf()

    override fun getItemCount(): Int = mFragmentList.size

    override fun createFragment(position: Int): Fragment  = mFragmentList[position]

    fun addFragment(fragment: Fragment, title: String){
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

    fun getTitle(position: Int) = mFragmentTitleList[position]
}