@file:Suppress("DEPRECATION")

package com.rivvana.naqos_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rivvana.naqos_app.databinding.ActivityMainBinding
import com.rivvana.naqos_app.fragment.HistoryFragment
import com.rivvana.naqos_app.fragment.ProfileFragment
import com.rivvana.naqos_app.fragment.SearchFragment
import com.rivvana.naqos_app.fragment.WishlistFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val fragmentSearch: Fragment = SearchFragment()
    val fragmentWishlist: Fragment = WishlistFragment()
    val fragmentHistory: Fragment = HistoryFragment()
    val fragmentProfile: Fragment = ProfileFragment()
    val fm: FragmentManager = supportFragmentManager
    var active: Fragment = fragmentSearch

    private lateinit var menu: Menu
    private lateinit var menuItem: MenuItem
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNav()
    }

    private fun setupBottomNav() {
        fm.beginTransaction().add(R.id.container, fragmentSearch).show(fragmentSearch).commit()
        fm.beginTransaction().add(R.id.container, fragmentWishlist).hide(fragmentWishlist).commit()
        fm.beginTransaction().add(R.id.container, fragmentHistory).hide(fragmentHistory).commit()
        fm.beginTransaction().add(R.id.container, fragmentProfile).hide(fragmentProfile).commit()

        bottomNavigationView = binding.navView
        menu = bottomNavigationView.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true

        bottomNavigationView.setOnNavigationItemSelectedListener {
                item ->
            when(item.itemId){
                R.id.navigation_search -> {
                    callFragment(0, fragmentSearch)
                }

                R.id.navigation_wishlist -> {
                    callFragment(1, fragmentWishlist)
                }

                R.id.navigation_history -> {
                    callFragment(2, fragmentHistory)
                }

                R.id.navigation_profile -> {
                    callFragment(3, fragmentProfile)
                }
            }
            false
        }

    }

    private fun callFragment(int: Int, fragment: Fragment) {
        menuItem = menu.getItem(int)
        menuItem.isChecked = true
        fm.beginTransaction().hide(active).show(fragment).commit()
        active = fragment
    }
}