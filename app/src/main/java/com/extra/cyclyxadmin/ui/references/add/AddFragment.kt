package com.extra.cyclyxadmin.ui.references.add


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.extra.cyclyxadmin.databinding.FragmentAddBinding
import com.extra.cyclyxadmin.model.ReferenceItem


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
        Log.d("ADD", "Args -> ${arguments.refType} and ${arguments.model}")

        viewModel = ViewModelProvider(
            this,
            AddVM.Factory(arguments.refType, arguments.model, application!!)
        ).get(AddVM::class.java)
        binding.viewModel = viewModel

        binding.btnSubmit.setOnClickListener{
            val item = ReferenceItem()
            item.title = ellipsizeString(binding.EdtCont.text.toString(),20)!!
            item.content = binding.EdtCont.text.toString()
            item.type = arguments.refType

            viewModel.onBtnClicked(item)
            activity?.onBackPressed()
        }

        return binding.root
    }

    private fun ellipsizeString(
        input: String?,
        maxCharacters: Int
    ): String? {
        require(maxCharacters >= 5) { "maxCharacters must be at least 3 because the ellipsis already take up 3 characters" }
        return if (input == null || input.length < maxCharacters) {
            input
        } else input.substring(
            0,
            maxCharacters - 3
        ) + "..."
    }
}
