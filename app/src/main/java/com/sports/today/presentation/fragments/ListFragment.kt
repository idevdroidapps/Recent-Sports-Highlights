package com.sports.today.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.sports.today.NavGraphDirections
import com.sports.today.R
import com.sports.today.databinding.FragmentListBinding
import com.sports.today.domain.entities.Sport
import com.sports.today.presentation.adapters.SportsListAdapter
import com.sports.today.presentation.injection.DependencyInject
import com.sports.today.presentation.viewmodels.SharedViewModel

class ListFragment : Fragment() {

    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var _binding: FragmentListBinding

    private val f1Adapter: SportsListAdapter by lazy {
        SportsListAdapter(object : (Sport) -> Unit {
            override fun invoke(sport: Sport) {
                sharedViewModel.selectedSportItem = sport
                sharedViewModel.navToDetails(true)
            }
        })
    }

    private val basketballAdapter: SportsListAdapter by lazy {
        SportsListAdapter(object : (Sport) -> Unit {
            override fun invoke(sport: Sport) {
                sharedViewModel.selectedSportItem = sport
                sharedViewModel.navToDetails(true)
            }
        })
    }

    private val tennisAdapter: SportsListAdapter by lazy {
        SportsListAdapter(object : (Sport) -> Unit {
            override fun invoke(sport: Sport) {
                sharedViewModel.selectedSportItem = sport
                sharedViewModel.navToDetails(true)
            }
        })
    }

    private val concatAdapter: ConcatAdapter by lazy {
        ConcatAdapter(f1Adapter, basketballAdapter, tennisAdapter)
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

        initRecyclerView()
        subscribeUI()

        return _binding.root
    }

    private fun initRecyclerView() {
        _binding.recyclerViewMain.apply {
            adapter = concatAdapter
        }
    }

    private fun subscribeUI() {

        sharedViewModel.f1Results.observe(viewLifecycleOwner, {
            f1Adapter.submitList(it)
        })

        sharedViewModel.basketballResults.observe(viewLifecycleOwner, {
            basketballAdapter.submitList(it)
        })

        sharedViewModel.tennisResults.observe(viewLifecycleOwner, {
            tennisAdapter.submitList(it)
        })

        sharedViewModel.shouldNavToDetails.observe(viewLifecycleOwner, { show ->
            if (show) {
                sharedViewModel.navToDetails(false)
                this@ListFragment.findNavController()
                    .navigate(NavGraphDirections.actionGlobalDetailsFragment())
            }
        })
    }
}