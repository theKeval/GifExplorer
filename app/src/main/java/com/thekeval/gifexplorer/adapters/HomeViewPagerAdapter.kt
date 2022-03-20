package com.thekeval.gifexplorer.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.thekeval.gifexplorer.views.FavouriteFragment
import com.thekeval.gifexplorer.views.TrendingFragment

const val TRENDING_PAGE_INDEX = 0
const val FAVOURITE_PAGE_INDEX = 1

class HomeViewPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    // mapping the ViewPager pages indexes to their respective Fragments
    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        TRENDING_PAGE_INDEX to { TrendingFragment() },
        FAVOURITE_PAGE_INDEX to { FavouriteFragment() }
    )

    override fun getItemCount(): Int {
        return tabFragmentsCreators.size
    }

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}