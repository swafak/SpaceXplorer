package com.example.features.explore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.features.databinding.FragmentExploreBinding
import com.example.features.history.HistoryAdapter
import com.example.features.launches.LaunchesAdapter
import com.example.network.model.data.CompanyResponse
import com.example.network.model.data.HistoryResponseItem
import com.example.network.model.data.LaunchesResponse
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExploreFragment : Fragment() {

    private val viewModel by viewModel<ExploreViewModel>()
    private val historyAdapter by lazy {
        HistoryAdapter()

    }

    private lateinit var binding: FragmentExploreBinding

    private val launchesAdapter by lazy {
        LaunchesAdapter(
            onClick = {
                Toast.makeText(requireContext(), "Launches clicked", Toast.LENGTH_SHORT).show()
            }
        )
    }
    private val history = mutableListOf<HistoryResponseItem>()
    private val company = mutableListOf<CompanyResponse>()
    private val launches = mutableListOf<LaunchesResponse>()



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
                    val action =
                        ExploreFragmentDirections.actionNavigationExploreToNavigationHistory(history = history.toTypedArray())
                    findNavController().navigate(action)
                }
            }
            arrowLaunches.setOnClickListener {
                val action = ExploreFragmentDirections.actionNavigationExploreToNavigationLaunches(
                    LaunchesResponse = launches.toTypedArray()
                )
                findNavController().navigate(action)
            }
        }
        setUpLaunchRecycler()
        setUpHistoryRecycler()
        observeData()

        viewModel.fetchLaunches()
        viewModel.fetchHistory()
        viewModel.fetchCompanyInfo()



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExploreBinding.inflate(inflater, container, false)



        return binding.root
    }

    private fun setUpHistoryRecycler() {


        binding.HistoryRecycler.adapter = historyAdapter
        binding.HistoryRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


    }

    private fun setUpHistoryObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.history.collect { historyList ->
                    historyAdapter.submitList(historyList)
                    history.addAll(historyList)
                    binding.arrowHistory.isVisible = history.isNotEmpty()

                }
            }

        }
    }



    private fun setUpLaunchRecycler() {

        binding.launchesRecycler.adapter = launchesAdapter
        binding.launchesRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setUpLaunchesObserver() {

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.launches.collectLatest { launchesList ->
                    launchesAdapter.submitList(launchesList)
                    launches.addAll(launchesList)
                    binding.arrowLaunches.isVisible = launches.isNotEmpty()
                }
            }
        }
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest { uiState ->
//                    when {
//                        is uiState.isLoading -> {}
//                        uiState.companyResponse != null -> {
//                        }


                        uiState.companyResponse?.let { companyResult ->
                            binding.website.text = companyResult.links.website
                            binding.twitter.text = companyResult.links.twitter
                            company.add(uiState.companyResponse)
                            binding.arrowCompany.isVisible = company.isNotEmpty()
                        }

                        uiState.history?.let {
                            historyAdapter.submitList(uiState.history)
                            history.addAll(uiState.history)
                            binding.arrowHistory.isVisible = history.isNotEmpty()
                        }

                         uiState.launches.let {
                            launchesAdapter.submitList(uiState.launches)
                            launches.addAll(uiState.launches)
                            binding.arrowLaunches.isVisible = launches.isNotEmpty()
                        }

                          uiState.error?. let {
                            println("Error occurred:  ${uiState.error}")
                            Toast.makeText(requireContext(), uiState.error, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }
    }



