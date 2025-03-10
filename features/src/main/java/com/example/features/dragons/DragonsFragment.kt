package com.example.features.dragons

import org.koin.androidx.viewmodel.ext.android.viewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.features.R
import com.example.features.databinding.FragmentDragonsBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DragonsFragment : Fragment() {


    private val viewModel: DragonsViewModel by viewModel()
    private lateinit var binding: FragmentDragonsBinding

    private val adapter by lazy{
        DragonAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUprecycler()
        viewModel.fetchLaunches()
        setUpObserver()


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDragonsBinding.inflate(inflater, container, false)

        return  binding.root
    }

    private fun setUprecycler(){

        binding.Recycler.adapter = adapter
        binding.Recycler.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setUpObserver(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.dragon.collect{response->
                    adapter.submitList(response)

                }
            }

            }

            }
}