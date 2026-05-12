package com.example.features.launches

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.features.model.toModel
import kotlinx.coroutines.launch

class LaunchesFragment : Fragment() {
    private val args by navArgs<LaunchesFragmentArgs>()
    private var launches by mutableStateOf(emptyList<com.example.features.model.Launch>())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    LaunchesScreen(launches = launches)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpData()
    }

    private fun setUpData() {
        lifecycleScope.launch {
            launches = args.LaunchesResponse.map { it.toModel() }
        }
    }
    private fun filterLaunches(query: String) {
        launches = if (query.isEmpty()) {
            launches
        } else {
            launches.filter {
                it.name.contains(query, ignoreCase = true)
            }
        }
    }
}
//    private fun setUpSearchView() {
//        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                query?.let { adapter.filter(it) }
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                newText?.let { adapter.filter(it) }
//                return true
//            }
//        })

