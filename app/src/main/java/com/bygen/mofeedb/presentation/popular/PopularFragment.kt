package com.bygen.mofeedb.presentation.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bygen.mofeedb.R
import com.bygen.mofeedb.databinding.FragmentPopularBinding
import com.google.android.material.tabs.TabLayoutMediator

class PopularFragment : Fragment() {

    private var _fragmentPopularBinding: FragmentPopularBinding? = null
    private val binding get() = _fragmentPopularBinding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentPopularBinding = FragmentPopularBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        binding.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = resources.getString(R.string.movies)
                1 -> tab.text = resources.getString(R.string.tvshows)
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.viewPager.adapter = null
        _fragmentPopularBinding = null
    }



}