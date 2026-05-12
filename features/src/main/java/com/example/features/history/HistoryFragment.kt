package com.example.features.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.features.databinding.FragmentHistoryBinding
import com.example.features.explore.HistoryCard
import com.example.features.launches.LaunchesScreen
import com.example.features.model.History
import com.example.features.model.toUiModel
import kotlinx.coroutines.launch

class HistoryFragment : Fragment() {
    private val args by navArgs<HistoryFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    LazyColumn {
                        items(args.history.toList()) { historyItem ->

                            HistoryCard(
                                history = historyItem.toUiModel()
                            )
                        }
                    }
                }
                }
            }
        }
    }