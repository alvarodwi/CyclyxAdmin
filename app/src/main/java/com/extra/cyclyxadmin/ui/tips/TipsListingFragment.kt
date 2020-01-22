package com.extra.cyclyxadmin.ui.tips


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.extra.cyclyxadmin.R
import com.extra.cyclyxadmin.databinding.ActivityMainBinding
import com.extra.cyclyxadmin.databinding.FragmentTipsListingBinding

/**
 * A simple [Fragment] subclass.
 */
class TipsListingFragment : Fragment() {
    private lateinit var binding : FragmentTipsListingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTipsListingBinding.inflate(inflater)
        binding.lifecycleOwner = this



        return binding.root
    }

}
