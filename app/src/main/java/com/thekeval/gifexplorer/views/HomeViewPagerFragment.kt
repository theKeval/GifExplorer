package com.thekeval.gifexplorer.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thekeval.gifexplorer.R
import com.thekeval.gifexplorer.databinding.FragmentHomeViewPagerBinding

class HomeViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // inflating layout via binding
        val binding = FragmentHomeViewPagerBinding.inflate(inflater, container, false)

        return binding.root
    }

}