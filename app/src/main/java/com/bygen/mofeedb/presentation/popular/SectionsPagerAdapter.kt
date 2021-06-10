package com.bygen.mofeedb.presentation.popular

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bygen.mofeedb.presentation.popular.mov.MovieFragment
import com.bygen.mofeedb.presentation.popular.tv.TelevisionFragment


class SectionsPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 ->  MovieFragment()
            1 ->  TelevisionFragment()
            else -> MovieFragment()
        }
    }


}