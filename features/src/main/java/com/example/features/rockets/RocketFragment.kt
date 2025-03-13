package com.example.features.rockets

import org.koin.androidx.viewmodel.ext.android.viewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionManager
import com.example.features.databinding.FragmentRocketBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RocketFragment : Fragment() {

    private val viewModel: RocketViewModel by viewModel()

    private lateinit var binding: FragmentRocketBinding

    private val adapter by lazy {
        RocketAdapter(
            onClick = { rocket ->
                val bottomDialogFragment = RocketDetailDialogFragment(rocket)
                bottomDialogFragment.show(parentFragmentManager , "RocketDetailDialog")
            }
        )

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecycler()
        setUpObserver()
        setUpSearchView()


            viewModel.fetchRockets()




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRocketBinding.inflate(inflater, container, false)

        return binding.root
    }

    private fun setUpRecycler(){

        binding.RocketRecycler.adapter = adapter
        binding.RocketRecycler.layoutManager = LinearLayoutManager(requireContext())

    }
    private fun renderLoading(isLoading: Boolean) {
        binding.apply {
            TransitionManager.beginDelayedTransition(binding.root)
            loading.isVisible = isLoading
            RocketRecycler.isGone = isLoading
        }
    }

    private fun setUpObserver(){

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.uiState.collectLatest { uiState->

                    renderLoading(uiState.isLoading)
                    uiState.rocketData.let {
                        adapter.submitFullList(uiState.rocketData)
                    }
                }
            }

        }

    }
    private fun setUpSearchView() {
        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { adapter.filter(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { adapter.filter(it) }
                return true
            }
        })
    }

}