package com.example.features.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.features.databinding.FragmentHistoryBinding
import kotlinx.coroutines.launch

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding

    private val adapter by lazy {
        HistoryAdapter()
    }

    private val args by navArgs<HistoryFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHistoryBinding.inflate(inflater, container, false)

        setUpRecyclerView()
        setUpObserver()


        return binding.root
    }

    private fun setUpRecyclerView() {

        binding.HistoryRecycler.adapter = adapter
        binding.HistoryRecycler.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setUpObserver() {
        lifecycleScope.launch {

            adapter.submitList(args.history.toList())
        }
    }

}