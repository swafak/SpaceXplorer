package com.example.features.ships

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.transition.TransitionManager
import com.example.features.databinding.FragmentShipsBinding
import com.example.features.model.toShips
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShipsFragment : Fragment() {
    private val viewModel: ShipsViewModel by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchShips()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    val uiState by viewModel.uiState.collectAsState()

                    shipScreen(
                        ship = uiState.ships.toShips(),
                        onClick = { response ->
                            val bottomDialogFragment = ShipsDetailsDialogFragment(response)
                            bottomDialogFragment.show(parentFragmentManager, "dialogDetails")
                        },
                        isLoading = uiState.isLoading,
                    )
                }
            }
        }
    }
}