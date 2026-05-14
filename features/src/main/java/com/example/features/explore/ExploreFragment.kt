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
import androidx.navigation.fragment.findNavController
import com.example.features.launches.LaunchesDetailDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

//class ExploreFragment : Fragment() {
//    private val viewModel by viewModel<ExploreViewModel>()
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        return ComposeView(requireContext()).apply {
//            setContent {
//                MaterialTheme {
//                    val uiState by viewModel.uiState.collectAsState()
//
//                    uiState.error?.let { error ->
//                        LaunchedEffect(error) {
//                            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
//                        }
//                    }
//
//                    ExploreScreen(
//                        uiState = uiState,
////                        onCompanyArrowClick = {
////                            uiState.companyModel?.let { company ->
////                                val action = ExploreFragmentDirections
////                                    .actionExploreToCompanyFragment(
////                                        CompanyResponse = company
////                                    )
////                                findNavController().navigate(action)
////                            }
////
////                        },
//                        onLaunchesArrowClick = {
//                            val action = ExploreFragmentDirections
//                                .actionNavigationExploreToNavigationLaunches(
//                                    LaunchesResponse = uiState.rawLaunches.toTypedArray()
//                                )
//                            findNavController().navigate(action)
//                        },
//                        onHistoryArrowClick = {
//                            val action = ExploreFragmentDirections
//                                .actionNavigationExploreToNavigationHistory(
//                                    history = uiState.rawHistory.toTypedArray()
//                                )
//                            findNavController().navigate(action)
//                        },
//
////
////                        onLaunchClick = { launch ->
////                            val raw = uiState.rawLaunches
////                                .find { it.id == launch.id }
////                            raw?.let {
////                                LaunchesDetailDialogFragment(it)
////                                    .show(parentFragmentManager, "DetailDialog")
////                            }
////                        }
////                    )
////                }
//            }
//        }
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        viewModel.getData()
//    }
//}

