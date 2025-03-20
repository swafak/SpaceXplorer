package com.example.features.favorites.ship

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.features.databinding.FragmentShipsBinding
import com.example.features.favorites.FavoritesViewModel
import com.example.features.ships.ShipsDetailsDialogFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavoriteShipsFragment : Fragment() {

    private lateinit var binding: FragmentShipsBinding
    private val viewModel: FavoritesViewModel by viewModel()
    private val adapter by lazy { FavoriteShipsAdapter(
            onClick = {
//                response->
//                val bottomDialogFragment = ShipsDetailsDialogFragment(response)
//                bottomDialogFragment.show(parentFragmentManager,"dialogDetails")
        }
    ) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObserver()
        setUpdata()
        setUpSearchView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShipsBinding.inflate(inflater, container, false)

        return binding.root
    }

    private fun setUpdata(){
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = GridLayoutManager(requireContext(), 2)

    }
    private fun setUpObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.favoriteShip.collectLatest { response ->
                    adapter.submitFullList(response)
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