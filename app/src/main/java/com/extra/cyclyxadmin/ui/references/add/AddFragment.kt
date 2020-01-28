package com.extra.cyclyxadmin.ui.references.add


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.extra.cyclyxadmin.R
import com.extra.cyclyxadmin.databinding.FragmentAddBinding
import com.extra.cyclyxadmin.model.ReferenceItem
import kotlinx.android.synthetic.main.fragment_add.*

/**
 * A simple [Fragment] subclass.
 */
class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private lateinit var viewModel: AddVM
    private val application by lazy {
        activity?.application
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater)

        val arguments = AddFragmentArgs.fromBundle(arguments!!)
        Log.d("ADD", "Args -> ${arguments.refType} and ${arguments.modelId}")

        viewModel = ViewModelProvider(
            this,
            AddVM.Factory(arguments.refType, arguments.modelId, application!!)
        ).get(AddVM::class.java)
        binding.viewModel = viewModel

        binding.btnSubmit.setOnClickListener{
            val item = ReferenceItem()
            item.title = binding.EdtTitle.text.toString()
            item.content = binding.EdtCont.text.toString()
            item.type = arguments.refType

            viewModel.onBtnClicked(item)
            activity?.onBackPressed()
        }

        return binding.root
    }
}
