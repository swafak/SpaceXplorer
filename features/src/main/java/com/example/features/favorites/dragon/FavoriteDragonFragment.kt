package com.example.features.favorites.dragon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.features.databinding.FragmentFavoriteDragonBinding
import com.example.features.favorites.FavoritesViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteDragonFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteDragonBinding
    private val adapter by lazy {
        FavoriteDragonAdapter(
            onClick = {}
        )
    }
    private val viewModel : FavoritesViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpData()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=  FragmentFavoriteDragonBinding.inflate(inflater, container, false)

    return binding.root
    }

    private fun setUpData() {
        binding.Recycler.adapter = adapter
        binding.Recycler.layoutManager = LinearLayoutManager(requireContext())

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.favDragon.collect { response ->
                    adapter.submitList(response)
                }
            }

        }
    }
        }