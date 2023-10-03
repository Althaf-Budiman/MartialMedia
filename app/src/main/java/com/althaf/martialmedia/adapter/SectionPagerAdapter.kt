package com.althaf.martialmedia.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.althaf.martialmedia.fragments.BoxingFragment
import com.althaf.martialmedia.fragments.MmaFragment
import com.althaf.martialmedia.fragments.MuayThaiFragment

class SectionPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MmaFragment()
            1 -> MuayThaiFragment()
            2 -> BoxingFragment()
            else -> MmaFragment()
        }
    }
}