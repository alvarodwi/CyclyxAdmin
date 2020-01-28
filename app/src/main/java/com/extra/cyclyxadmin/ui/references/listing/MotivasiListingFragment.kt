package com.extra.cyclyxadmin.ui.references.listing


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

import com.extra.cyclyxadmin.databinding.FragmentMotivasiListingBinding
import com.extra.cyclyxadmin.ui.adapter.MainRVAdapter
import com.extra.cyclyxadmin.ui.references.BaseReferencesVM
import com.extra.cyclyxadmin.ui.references.ReferencesFragmentDirections
import com.extra.cyclyxadmin.utils.firebaseConstants.MOTIVASI_ITEM
import com.extra.cyclyxadmin.utils.actionConstants.DELETE_ITEM
import com.extra.cyclyxadmin.utils.actionConstants.EDIT_ITEM

/**
 * A simple [Fragment] subclass.
 */
class MotivasiListingFragment : Fragment() {
    private lateinit var binding : FragmentMotivasiListingBinding
    private lateinit var viewModel: BaseReferencesVM
    private val application by lazy {
        activity?.application
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMotivasiListingBinding.inflate(inflater)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this,
            BaseReferencesVM.Factory(
                MOTIVASI_ITEM,
                application!!
            )
        ).get(BaseReferencesVM::class.java)
        binding.viewModel = viewModel

        binding.rvMotivasi.layoutManager = LinearLayoutManager(context)
        val adapter = MainRVAdapter(MainRVAdapter.MainClickListener{ uid, action ->
            when(action){
                EDIT_ITEM -> {
                    this.findNavController().navigate(
                        ReferencesFragmentDirections.navToAddFromFragment(
                            MOTIVASI_ITEM, uid
                        )
                    )
                }
                DELETE_ITEM -> {
                    Log.d("ADD","Deleted -> $uid")
                    viewModel.onDeleteItem(uid)
                }
            }
        })
        binding.rvMotivasi.adapter = adapter

        viewModel.referenceItem.observe(viewLifecycleOwner, Observer {items ->
            binding.srlMotivasi.isRefreshing = true
            items?.let{
                adapter.submitList(it)
                binding.srlMotivasi.isRefreshing = false
            }
        })

        binding.srlMotivasi.setOnRefreshListener {
            viewModel.onRefresh()
        }

        return binding.root
    }
}
