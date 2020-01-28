package com.extra.cyclyxadmin.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.extra.cyclyxadmin.ui.references.listing.MotivasiListingFragment
import com.extra.cyclyxadmin.ui.references.listing.TipsListingFragment
import com.extra.cyclyxadmin.ui.references.listing.TutorialListingFragment

class MainViewPager(private val context: Context, fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        var fragment : Fragment? = null
        when(position){
            0 -> fragment =
                TipsListingFragment()
            1 -> fragment =
                TutorialListingFragment()
            2 -> fragment =
                MotivasiListingFragment()
        }
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            1 -> "Tutorial"
            2 -> "Motivasi"
            else -> "Tips"
        }
    }

    override fun getCount(): Int = 3
}