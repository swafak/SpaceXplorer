package com.example.features.favorites

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.features.favorites.dragon.FavoriteDragonFragment
import com.example.features.favorites.rocket.FavoriteRocketFragment
import com.example.features.favorites.ship.FavoriteShipsFragment

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