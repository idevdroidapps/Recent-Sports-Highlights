package com.sports.today.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sports.today.R
import com.sports.today.databinding.FragmentDetailsBinding
import com.sports.today.presentation.injection.DependencyInject
import com.sports.today.presentation.viewmodels.SharedViewModel

class DetailsFragment : BottomSheetDialogFragment() {

    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var _binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        activity?.let {
            sharedViewModel = ViewModelProvider(
                it,
                DependencyInject.provideSharedViewModelFactory()
            ).get(SharedViewModel::class.java)
        }

        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)

        _binding.textViewDetailsHeadline.text = sharedViewModel.selectedText

        return _binding.root
    }

}