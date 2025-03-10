package com.example.features.favorites

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FavoritePagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FavoriteShipsFragment()
            1 -> FavoriteDragonFragment()
            2 -> FavoriteRocketFragment()
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }
}