package com.extra.cyclyxadmin.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.extra.cyclyxadmin.ui.tips.TipsListingFragment
import com.extra.cyclyxadmin.ui.tutorial.TutorialListingFragment

class MainViewPager(private val context: Context, fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        var fragment : Fragment? = null
        when(position){
            0 -> fragment = TipsListingFragment()
            1 -> fragment = TutorialListingFragment()
        }
        return fragment as Fragment
    }

    override fun getCount(): Int = 2
}