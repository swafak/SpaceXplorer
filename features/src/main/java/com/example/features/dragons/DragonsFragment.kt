package com.example.features.dragons

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionManager
import com.example.features.databinding.FragmentDragonsBinding
import com.example.features.favorites.dragon.FavoriteDragonViewModel
import com.example.features.model.Dragon
import com.example.features.model.toEntity
import com.example.features.model.toShipsResponse
import com.example.features.ships.ShipDetailContent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DragonsFragment() : Fragment(

) {
    private val viewModel: DragonsViewModel by viewModel()
    private val favoritesViewModel: FavoriteDragonViewModel by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchDragon()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    val uiState by viewModel.uiState.collectAsState()
                    val favUiState by favoritesViewModel.uiState.collectAsState()

                    DragonScreen(
                        dragon = uiState.dragon,
                        isLoading = uiState.isLoading,
                        onFavoriteClick = { dragon ->
                            favoritesViewModel.isFavDragon(dragon.id)
                            favoritesViewModel.toggleFavorite(dragon)
                        },
                        isFavorite = { id ->
                            favoritesViewModel.uiState.value.isFavoriteState
                        }
                    )
                }
            }
        }
    }
}