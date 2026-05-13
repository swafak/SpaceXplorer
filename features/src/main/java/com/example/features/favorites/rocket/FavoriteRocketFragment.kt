package com.example.features.favorites.rocket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.features.databinding.FragmentFavoriteRocketBinding
import com.example.features.model.toRocketEntity
import com.example.features.rockets.RocketDetailDialogFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteRocketFragment : Fragment() {

    private val viewModel: FavoriteRocketViewModel by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFavRocket()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    val uiState by viewModel.uiState.collectAsState()

                    FavRocketScreen(
                        rocket = uiState.favoriteRocket,

                        onClick = { rocket ->
                            val bottomDialogFragment = RocketDetailDialogFragment(
                                rocket
                            )
                            bottomDialogFragment.show(parentFragmentManager, "RocketDetailDialog")
                        }
                    )

                }

            }
        }
    }
}
