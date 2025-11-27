package com.example.ca3.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.navigation.NavigationView
import com.example.ca3.R
import com.example.ca3.ui.adapter.ViewPagerAdapter
import com.example.ca3.ui.fragments.HomeFragment
import com.example.ca3.ui.fragments.CartFragment
import com.example.ca3.ui.fragments.OrdersFragment
import com.example.ca3.ui.fragments.SettingsFragment
import com.example.ca3.ui.fragments.UserProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        viewPager = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tab_layout)

        setupActionBar()
        setupViewPager()
        setupNavigationDrawer()
    }

    private fun setupActionBar() {
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.nav_open_drawer,
            R.string.nav_close_drawer
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun setupViewPager() {
        val fragments = listOf(
            HomeFragment(),
            CartFragment(),
            OrdersFragment(),
            UserProfileFragment(),
            SettingsFragment()
        )
        val titles = listOf("Home", "Cart", "Orders", "User", "Settings")

        viewPager.adapter = ViewPagerAdapter(this, fragments)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()
    }

    private fun setupNavigationDrawer() {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> viewPager.currentItem = 0
                R.id.nav_cart -> viewPager.currentItem = 1
                R.id.nav_orders -> viewPager.currentItem = 2
                R.id.nav_profile -> viewPager.currentItem = 3
                R.id.nav_settings -> viewPager.currentItem = 4
            }
            drawerLayout.closeDrawers()
            true
        }
    }
}
