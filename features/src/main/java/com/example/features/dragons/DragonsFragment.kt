package com.example.features.dragons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionManager
import com.example.features.databinding.FragmentDragonsBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DragonsFragment : Fragment() {


    private val viewModel: DragonsViewModel by viewModel()
    private lateinit var binding: FragmentDragonsBinding

    private val adapter by lazy {
        DragonAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUprecycler()
        viewModel.fetchDragon()
        setUpObserver()


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDragonsBinding.inflate(inflater, container, false)

        return binding.root
    }

    private fun setUprecycler() {

        binding.Recycler.adapter = adapter
        binding.Recycler.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun renderLoading(isLoading: Boolean) {
        binding.apply {
            TransitionManager.beginDelayedTransition(binding.root)
            loading.isVisible = isLoading
            Recycler.isGone = isLoading
        }
    }

    private fun setUpObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.uiState.collectLatest { uistate ->

                    renderLoading(uistate.isLoading)
                    uistate.dragonInfo.let {
                        adapter.submitList(uistate.dragonInfo)
                    }
                }

            }
        }

    }

}
