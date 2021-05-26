package com.sports.today.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.sports.today.NavGraphDirections
import com.sports.today.R
import com.sports.today.databinding.FragmentListBinding
import com.sports.today.presentation.adapters.BasketballAdapter
import com.sports.today.presentation.adapters.Formula1Adapter
import com.sports.today.presentation.adapters.TennisAdapter
import com.sports.today.presentation.injection.DependencyInject
import com.sports.today.presentation.viewmodels.SharedViewModel

class ListFragment : Fragment() {

    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var _binding: FragmentListBinding

    private val f1Adapter: Formula1Adapter by lazy {
        Formula1Adapter(object : (View) -> Unit {
            override fun invoke(view: View) {
                navToDetails(view)
            }
        })
    }

    private val basketballAdapter: BasketballAdapter by lazy {
        BasketballAdapter(object : (View) -> Unit {
            override fun invoke(view: View) {
                navToDetails(view)
            }
        })
    }

    private val tennisAdapter: TennisAdapter by lazy {
        TennisAdapter(object : (View) -> Unit {
            override fun invoke(view: View) {
                navToDetails(view)
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

        initViews()
        subscribeUI()

        return _binding.root
    }

    private fun initViews() {
        // ProgressBar
        _binding.shouldShowList = false
        // Button
        _binding.button.setOnClickListener {
            _binding.progressBar.visibility = View.VISIBLE
            sharedViewModel.fetchSports()
        }
        // RecyclerView
        _binding.recyclerViewMain.apply {
            adapter = concatAdapter
        }
    }

    private fun navToDetails(view: View) {
        sharedViewModel.selectedText = ((view as ConstraintLayout)[1] as TextView).text.toString()
        sharedViewModel.navToDetails(true)
    }

    private fun subscribeUI() {

        sharedViewModel.f1Results.observe(viewLifecycleOwner, {
            f1Adapter.submitList(it)
            _binding.shouldShowList = true
            _binding.progressBar.visibility = View.GONE
        })

        sharedViewModel.basketballResults.observe(viewLifecycleOwner, {
            basketballAdapter.submitList(it)
            _binding.shouldShowList = true
            _binding.progressBar.visibility = View.GONE
        })

        sharedViewModel.tennisResults.observe(viewLifecycleOwner, {
            tennisAdapter.submitList(it)
            _binding.shouldShowList = true
            _binding.progressBar.visibility = View.GONE
        })

        sharedViewModel.shouldNavToDetails.observe(viewLifecycleOwner, { show ->
            if (show) {
                sharedViewModel.navToDetails(false)
                this@ListFragment.findNavController()
                    .navigate(NavGraphDirections.actionGlobalDetailsFragment())
            }
        })

        sharedViewModel.retrievalError.observe(viewLifecycleOwner, {
            if(it){
                sharedViewModel.resetOnError(false)
                _binding.shouldShowList = false
                _binding.progressBar.visibility = View.GONE
            }

        })
    }
}