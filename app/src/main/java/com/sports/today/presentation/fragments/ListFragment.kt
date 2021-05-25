package com.sports.today.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sports.today.NavGraphDirections
import com.sports.today.R
import com.sports.today.databinding.FragmentListBinding
import com.sports.today.domain.entities.Highlight
import com.sports.today.presentation.adapters.HighlightListAdapter
import com.sports.today.presentation.injection.DependencyInject
import com.sports.today.presentation.viewmodels.SharedViewModel

class ListFragment : Fragment() {

    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var _binding: FragmentListBinding

    private val listAdapter: HighlightListAdapter by lazy {
        HighlightListAdapter(object : (Highlight) -> Unit {
            override fun invoke(highlight: Highlight) {
                sharedViewModel.selectedHighlight = highlight
                sharedViewModel.showDetails(true)
            }
        })
    }

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
            DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)

        initAdapter()
        subscribeUI()

        return _binding.root
    }

    private fun initAdapter() {
        _binding.recyclerViewMain.apply {
            adapter = listAdapter
        }
    }

    private fun subscribeUI() {

        sharedViewModel.highlights.observe(viewLifecycleOwner, {
            listAdapter.submitList(it)
        })

        sharedViewModel.shouldShowDetails.observe(viewLifecycleOwner, { show ->
            if (show) {
                sharedViewModel.showDetails(false)
                this@ListFragment.findNavController()
                    .navigate(NavGraphDirections.actionGlobalDetailsFragment())
            }
        })
    }
}