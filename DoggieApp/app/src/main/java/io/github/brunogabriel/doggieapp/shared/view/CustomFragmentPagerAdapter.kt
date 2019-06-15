package io.github.brunogabriel.doggieapp.shared.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * Created by brunogabriel on 2019-06-15.
 */
class CustomFragmentPagerAdapter(
    fm: FragmentManager,
    private val fragmentsWithTitle : List<Pair<Fragment, String>>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int) = fragmentsWithTitle[position].first

    override fun getCount() = fragmentsWithTitle.size

    override fun getPageTitle(position: Int) = fragmentsWithTitle[position].second
}