package com.extra.cyclyxadmin.ui.references


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.extra.cyclyxadmin.R
import com.extra.cyclyxadmin.databinding.FragmentReferencesBinding
import com.extra.cyclyxadmin.ui.adapter.MainViewPager
import com.extra.cyclyxadmin.utils.firebaseConstants.MOTIVASI_ITEM
import com.extra.cyclyxadmin.utils.firebaseConstants.TIPS_ITEM
import com.extra.cyclyxadmin.utils.firebaseConstants.TUTORIAL_ITEM

/**
 * A simple [Fragment] subclass.
 */
class ReferencesFragment : Fragment() {
    private lateinit var binding : FragmentReferencesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReferencesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainVPAdapter = MainViewPager(
            requireContext(),
            childFragmentManager
        )

        var isOpen = false
        binding.fabMain.setOnClickListener {
            if (isOpen){
                binding.tvBtn1.visibility = View.INVISIBLE
                binding.tvBtn2.visibility = View.INVISIBLE
                binding.tvBtn3.visibility = View.INVISIBLE
                binding.tvBtn1.startAnimation(android.view.animation.AnimationUtils.loadAnimation(requireContext(), R.anim.fab_close))
                binding.tvBtn2.startAnimation(android.view.animation.AnimationUtils.loadAnimation(requireContext(), R.anim.fab_close))
                binding.tvBtn3.startAnimation(android.view.animation.AnimationUtils.loadAnimation(requireContext(), R.anim.fab_close))
                binding.btnMot.startAnimation(android.view.animation.AnimationUtils.loadAnimation(requireContext(), R.anim.fab_close))
                binding.btnTutor.startAnimation(android.view.animation.AnimationUtils.loadAnimation(requireContext(), R.anim.fab_close))
                binding.btnTips.startAnimation(android.view.animation.AnimationUtils.loadAnimation(requireContext(), R.anim.fab_close))
                binding.fabMain.startAnimation(android.view.animation.AnimationUtils.loadAnimation(requireContext(), R.anim.fab_rotate_anticlock))
                binding.btnMot.isClickable = false
                binding.btnTutor.isClickable = false
                binding.btnTips.isClickable = false
                isOpen = false
            } else {
                binding.tvBtn1.visibility = View.VISIBLE
                binding.tvBtn2.visibility = View.VISIBLE
                binding.tvBtn3.visibility = View.VISIBLE
                binding.tvBtn1.startAnimation(android.view.animation.AnimationUtils.loadAnimation(requireContext(), R.anim.fab_open))
                binding.tvBtn2.startAnimation(android.view.animation.AnimationUtils.loadAnimation(requireContext(), R.anim.fab_open))
                binding.tvBtn3.startAnimation(android.view.animation.AnimationUtils.loadAnimation(requireContext(), R.anim.fab_open))
                binding.btnMot.startAnimation(android.view.animation.AnimationUtils.loadAnimation(requireContext(), R.anim.fab_open))
                binding.btnTutor.startAnimation(android.view.animation.AnimationUtils.loadAnimation(requireContext(), R.anim.fab_open))
                binding.btnTips.startAnimation(android.view.animation.AnimationUtils.loadAnimation(requireContext(), R.anim.fab_open))
                binding.fabMain.startAnimation(android.view.animation.AnimationUtils.loadAnimation(requireContext(), R.anim.fab_rotate_clock))
                binding.btnMot.isClickable = true
                binding.btnTutor.isClickable = true
                binding.btnTips.isClickable = true
                isOpen = true
            }
        }

        binding.btnTips.setOnClickListener{
            this.findNavController().navigate(ReferencesFragmentDirections.navToAddFromFragment(TIPS_ITEM,null))
        }

        binding.btnTutor.setOnClickListener{
            this.findNavController().navigate(ReferencesFragmentDirections.navToAddFromFragment(TUTORIAL_ITEM,null))
        }

        binding.btnMot.setOnClickListener{
            this.findNavController().navigate(ReferencesFragmentDirections.navToAddFromFragment(MOTIVASI_ITEM,null))
        }

        binding.container.adapter = mainVPAdapter
        binding.tabs.setupWithViewPager(binding.container)
    }


}
