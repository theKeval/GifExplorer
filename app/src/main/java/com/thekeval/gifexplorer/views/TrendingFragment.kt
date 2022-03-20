package com.thekeval.gifexplorer.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thekeval.gifexplorer.R
import com.thekeval.gifexplorer.databinding.FragmentTrendingBinding

class TrendingFragment : Fragment() {

    private lateinit var binding: FragmentTrendingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflating the layout through databinding
        binding = FragmentTrendingBinding.inflate(inflater, container, false)


        return binding.root
    }

}