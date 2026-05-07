package com.example.features.explore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionManager
import com.example.features.databinding.FragmentExploreBinding
import com.example.features.history.HistoryAdapter
import com.example.features.launches.LaunchesAdapter
import com.example.features.launches.LaunchesDetailDialogFragment
import com.example.network.model.data.CompanyResponse
import com.example.network.model.data.HistoryResponseItem
import com.example.network.model.data.LaunchesResponse
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExploreFragment : Fragment() {
    private val viewModel by viewModel<ExploreViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    val uiState by viewModel.uiState.collectAsState()

                    uiState.error?.let { error ->
                        LaunchedEffect(error) {
                            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                        }
                    }

                    ExploreScreen(
                        uiState = uiState,
                        onCompanyArrowClick = {
                            uiState.rawCompany?.let { company ->
                                val action = ExploreFragmentDirections
                                    .actionExploreToCompanyFragment(
                                        CompanyResponse = company
                                    )
                                findNavController().navigate(action)
                            }

                        },
                        onLaunchesArrowClick = {
                            val action = ExploreFragmentDirections
                                .actionNavigationExploreToNavigationLaunches(
                                    LaunchesResponse = uiState.rawLaunches.toTypedArray()
                                )
                            findNavController().navigate(action)
                        },
                        onHistoryArrowClick = {
                            val action = ExploreFragmentDirections
                                .actionNavigationExploreToNavigationHistory(
                                    history = uiState.rawHistory.toTypedArray()
                                )
                            findNavController().navigate(action)                        },
                        onLaunchClick = { launch ->
                            val raw = uiState.rawLaunches
                                .find { it.id == launch.id }
                            raw?.let {
                                LaunchesDetailDialogFragment(it)
                                    .show(parentFragmentManager, "DetailDialog")
                            }
                        }
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData()
    }
}

