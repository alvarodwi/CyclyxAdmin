package com.extra.cyclyxadmin.ui.references.tutorial


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.extra.cyclyxadmin.R
import com.extra.cyclyxadmin.databinding.FragmentTutorialListingBinding
import com.extra.cyclyxadmin.ui.adapter.MainRVAdapter
import com.extra.cyclyxadmin.ui.references.BaseReferencesVM
import com.extra.cyclyxadmin.ui.references.ReferencesFragmentDirections
import com.extra.cyclyxadmin.utils.actionConstants
import com.extra.cyclyxadmin.utils.firebaseConstants

/**
 * A simple [Fragment] subclass.
 */
class TutorialListingFragment : Fragment() {
    private lateinit var binding : FragmentTutorialListingBinding
    private lateinit var viewModel: BaseReferencesVM
    private val application by lazy {
        activity?.application
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTutorialListingBinding.inflate(inflater)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this,
            BaseReferencesVM.Factory(firebaseConstants.TUTORIAL_ITEM,application!!)).get(
            BaseReferencesVM::class.java)
        binding.viewModel = viewModel

        binding.rvTutorial.layoutManager = LinearLayoutManager(context)
        val adapter = MainRVAdapter(MainRVAdapter.MainClickListener{ uid, action ->
            when(action){
                actionConstants.EDIT_ITEM -> {
                    this.findNavController().navigate(
                        ReferencesFragmentDirections.navToAddFromFragment(
                            firebaseConstants.TUTORIAL_ITEM,uid))
                }
                actionConstants.DELETE_ITEM -> {
                    Log.d("ADD","Deleted -> $uid")
                    viewModel.onDeleteItem(uid)
                }
            }
        })
        binding.rvTutorial.adapter = adapter

        viewModel.referenceItem.observe(viewLifecycleOwner, Observer {items ->
            binding.srlTutorial.isRefreshing = true
            items?.let{
                adapter.submitList(it)
                binding.srlTutorial.isRefreshing = false
            }
        })

        binding.srlTutorial.setOnRefreshListener {
            viewModel.onRefresh()
        }

        return binding.root
    }


}
