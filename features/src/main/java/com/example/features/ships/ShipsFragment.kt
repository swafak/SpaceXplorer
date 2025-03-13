package com.example.features.ships

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.transition.TransitionManager
import com.example.features.R
import com.example.features.databinding.FragmentShipsBinding
import com.example.network.model.data.ShipsResponseItem
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShipsFragment : Fragment() {


    private val viewModel: ShipsViewModel by viewModel()
    private lateinit var binding: FragmentShipsBinding
    private val adapter by lazy {
        ShipsAdapter(
            onClick = {
                Toast.makeText(requireContext(),"ships",Toast.LENGTH_SHORT).show()
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecycler()
        setUpObserver()
        viewModel.fetchShips()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShipsBinding.inflate(inflater, container, false)

        return binding.root
    }

    private fun setUpRecycler(){

        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = GridLayoutManager(requireContext(),3)

    }
    private fun renderLoading(isLoading: Boolean) {
        binding.apply {
            TransitionManager.beginDelayedTransition(binding.root)
            loading.isVisible = isLoading
            recycler.isGone = isLoading
        }
    }

    private fun setUpObserver(){

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.uiState.collectLatest { uistate ->

                    renderLoading(uistate.isLoading)

                    uistate.ships.let {
                        adapter.submitList(uistate.ships)

                    }

                }
            }

        }

    }
}