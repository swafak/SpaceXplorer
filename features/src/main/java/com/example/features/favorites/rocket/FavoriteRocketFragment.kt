package com.example.features.favorites.rocket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    private lateinit var binding: FragmentFavoriteRocketBinding
    private val adapter by lazy {
        FavoriteRocketAdapter(
            onClick = { rocket ->
                val bottomDialogFragment = RocketDetailDialogFragment(
                    rocket
                )
                bottomDialogFragment.show(parentFragmentManager , "RocketDetailDialog")
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
        viewModel.getFavRocket()
        observeViewModel()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteRocketBinding.inflate(inflater, container, false)

        return binding.root
    }

    private fun setUpRecycler() {
        binding.Recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.Recycler.adapter = adapter
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest { response ->
                    response.favoriteRocket.let { rocket ->
                        adapter.submitFullList(rocket.toRocketEntity())
                    }
                }
            }
        }
    }
}
