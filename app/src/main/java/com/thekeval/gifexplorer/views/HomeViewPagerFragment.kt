package com.thekeval.gifexplorer.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.thekeval.gifexplorer.R
import com.thekeval.gifexplorer.adapters.FAVOURITE_PAGE_INDEX
import com.thekeval.gifexplorer.adapters.HomeViewPagerAdapter
import com.thekeval.gifexplorer.adapters.TRENDING_PAGE_INDEX
import com.thekeval.gifexplorer.databinding.FragmentHomeViewPagerBinding

class HomeViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // inflating layout via binding
        val binding = FragmentHomeViewPagerBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = HomeViewPagerAdapter(this)

        // Set the icon and text for each tab
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return binding.root
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            TRENDING_PAGE_INDEX -> R.drawable.trending_tab_selector
            FAVOURITE_PAGE_INDEX -> R.drawable.favorite_tab_selector
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            TRENDING_PAGE_INDEX -> getString(R.string.trending_tab_title)
            FAVOURITE_PAGE_INDEX -> getString(R.string.favorite_tab_title)
            else -> null
        }
    }

}