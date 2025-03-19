package com.example.features.launches

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.features.databinding.FragmentLaunchesBinding
import com.example.features.rockets.RocketDetailDialogFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LaunchesFragment : Fragment() {

    private lateinit var binding: FragmentLaunchesBinding

    private val args by navArgs<LaunchesFragmentArgs>()

    private val adapter by lazy {
        LaunchesAdapter(
            onClick = {launches->
                Toast.makeText(requireContext(), "Launches clicked", Toast.LENGTH_SHORT).show()
                val bottomDialogFragment = LaunchesDetailDialogFragment(launches)
                bottomDialogFragment.show(parentFragmentManager , "DetailDialog")
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpData()
        setUpSearchView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLaunchesBinding.inflate(inflater, container, false)

        return binding.root
    }
    private fun setUpData(){
        binding.Recycler.adapter = adapter
        binding.Recycler.layoutManager = GridLayoutManager(requireContext(), 3)

        lifecycleScope.launch {
            adapter.submitFullList(args.LaunchesResponse.toList())
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