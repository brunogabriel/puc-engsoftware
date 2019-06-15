package io.github.brunogabriel.doggieapp.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentPagerAdapter
import io.github.brunogabriel.doggieapp.R
import io.github.brunogabriel.doggieapp.main.petlist.PetListFragment
import io.github.brunogabriel.doggieapp.main.settings.SettingsFragment
import io.github.brunogabriel.doggieapp.shared.view.CustomFragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by brunogabriel on 2019-06-15.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var petListFragment: PetListFragment
    private lateinit var settingsFragment: SettingsFragment
    private lateinit var customFragmentPagerAdapter: FragmentPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
    }

    private fun setupView() {
        toolbar.apply {
            setSupportActionBar(this)
        }

        petListFragment = PetListFragment()
        settingsFragment = SettingsFragment()

        customFragmentPagerAdapter = CustomFragmentPagerAdapter(supportFragmentManager, listOf(
            Pair(petListFragment, getString(R.string.my_pets)),
            Pair(settingsFragment, getString(R.string.settings))))

        view_pager.apply {
            adapter = customFragmentPagerAdapter
            offscreenPageLimit = customFragmentPagerAdapter.count
        }

        bottom_nav.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.action_pets -> {
                    view_pager.currentItem = 0
                    changeTitle(0)
                    true
                }

                R.id.action_settings -> {
                    view_pager.currentItem = 1
                    changeTitle(1)
                    true
                }

                else -> false
            }
        }

        changeTitle(0)
    }

    private fun changeTitle(position: Int) {
        toolbar.title = customFragmentPagerAdapter.getPageTitle(position)
    }
}