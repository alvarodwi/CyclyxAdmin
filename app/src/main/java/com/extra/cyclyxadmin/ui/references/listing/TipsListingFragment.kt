package com.extra.cyclyxadmin.ui.references.tips


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
import com.extra.cyclyxadmin.databinding.FragmentTipsListingBinding
import com.extra.cyclyxadmin.ui.adapter.MainRVAdapter
import com.extra.cyclyxadmin.ui.references.BaseReferencesVM
import com.extra.cyclyxadmin.ui.references.ReferencesFragmentDirections
import com.extra.cyclyxadmin.utils.actionConstants
import com.extra.cyclyxadmin.utils.firebaseConstants

/**
 * A simple [Fragment] subclass.
 */
class TipsListingFragment : Fragment() {
    private lateinit var binding : FragmentTipsListingBinding
    private lateinit var viewModel: BaseReferencesVM
    private val application by lazy {
        activity?.application
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTipsListingBinding.inflate(inflater)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this,
            BaseReferencesVM.Factory(firebaseConstants.TIPS_ITEM,application!!)).get(
            BaseReferencesVM::class.java)
        binding.viewModel = viewModel

        binding.rvTips.layoutManager = LinearLayoutManager(context)
        val adapter = MainRVAdapter(MainRVAdapter.MainClickListener{ uid, action ->
            when(action){
                actionConstants.EDIT_ITEM -> {
                    this.findNavController().navigate(
                        ReferencesFragmentDirections.navToAddFromFragment(
                            firebaseConstants.TIPS_ITEM,uid))
                }
                actionConstants.DELETE_ITEM -> {
                    Log.d("ADD","Deleted -> $uid")
                    viewModel.onDeleteItem(uid)
                }
            }
        })
        binding.rvTips.adapter = adapter

        viewModel.referenceItem.observe(viewLifecycleOwner, Observer {items ->
            binding.srlTips.isRefreshing = true
            items?.let{
                adapter.submitList(it)
                binding.srlTips.isRefreshing = false
            }
        })

        binding.srlTips.setOnRefreshListener {
            viewModel.onRefresh()
        }

        return binding.root
    }

}
