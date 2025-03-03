package com.example.features.launches

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.features.R
import com.example.features.databinding.FragmentLaunchesBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LaunchesFragment : Fragment() {

    private lateinit var binding: FragmentLaunchesBinding

    private val viewModel: LaunchesViewModel by viewModel()

    private val args by navArgs<LaunchesFragmentArgs>()

    private val adapter by lazy {
        LaunchesAdapter(
            onClick = {

            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLaunchesBinding.inflate(inflater, container, false)

        binding.Recycler.adapter = adapter
        binding.Recycler.layoutManager = GridLayoutManager(requireContext(),3)

        lifecycleScope.launch {

            adapter.submitList(args.LaunchesResponse.toList())
        }

        return binding.root
    }
}