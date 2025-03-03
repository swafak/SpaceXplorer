package com.example.features.explore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.features.R
import com.example.features.databinding.FragmentExploreBinding
import com.example.features.history.HistoryAdapter
import com.example.features.launches.LaunchesAdapter
import com.example.network.model.data.CompanyResponse
import com.example.network.model.data.HistoryResponseItem
import com.example.network.model.data.LaunchesResponse
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExploreFragment : Fragment() {

    private val viewModel by lazy {
        requireParentFragment().getViewModel<ExploreViewModel>()
    }

//    private val viewModel by viewModel<ExploreViewModel>()
    private val historyAdapter by lazy {
        HistoryAdapter(
            onClick = {

            }
        )

    }

    private lateinit var binding: FragmentExploreBinding

    private val launchesAdapter by lazy {
        LaunchesAdapter(
            onClick = {

            }
        )
    }
    private val history = mutableListOf<HistoryResponseItem>()
    private val company = mutableListOf<CompanyResponse>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            arrowCompany.setOnClickListener {
                val action = ExploreFragmentDirections.actionExploreToCompanyFragment(
                   CompanyResponse = company.first()
                )
                findNavController().navigate(action)

            }
            arrowHistory.apply {
                setOnClickListener {
                    val action = ExploreFragmentDirections.actionNavigationExploreToNavigationHistory(history = history.toTypedArray())
                    findNavController().navigate(action)
                }
            }
            arrowLaunches.setOnClickListener {
                val action = ExploreFragmentDirections.actionNavigationExploreToNavigationLaunches()
                findNavController().navigate(action)
            }
        }

        fetchHistoryData()
        fetchCompanyData()
        setCompanyObservers()
        fetchLaunchesData()
        setUpLaunchesObserver()




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun fetchHistoryData() {

        viewModel.fetchHistory()

        binding.HistoryRecycler.adapter = historyAdapter
        binding.HistoryRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        lifecycleScope.launch {

            viewModel.history.collectLatest { historyList ->
                historyAdapter.submitList(historyList)
                history.addAll(historyList)
                binding.arrowHistory.isVisible = history.isNotEmpty()

            }
        }
    }

    private fun fetchCompanyData(){
        viewModel.fetchCompanyInfo()

    }


    private fun setCompanyObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.companyInfo.collect { result ->
                    result?.let { safeResult ->
                        binding.apply {
                            website.text = safeResult.links.website
                            twitter.text = safeResult.links.twitter
                        }
                        company.add(safeResult)
                    }

                }
            }
        }
    }
    private fun fetchLaunchesData(){
        viewModel.fetchLaunches()

        binding.launchesRecycler.adapter = launchesAdapter
        binding.launchesRecycler.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setUpLaunchesObserver(){

        lifecycleScope.launch {
            viewModel.launches.collectLatest { launchesList->
                launchesAdapter.submitList(launchesList)
            }
        }
    }


}