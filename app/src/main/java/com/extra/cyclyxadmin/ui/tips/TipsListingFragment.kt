package com.extra.cyclyxadmin.ui.tips


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationSet
import androidx.vectordrawable.graphics.drawable.AnimationUtilsCompat

import com.extra.cyclyxadmin.R
import com.extra.cyclyxadmin.databinding.ActivityMainBinding
import com.extra.cyclyxadmin.databinding.FragmentTipsListingBinding
import com.google.android.material.animation.AnimationUtils
import kotlinx.android.synthetic.main.fragment_tips_listing.*

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
