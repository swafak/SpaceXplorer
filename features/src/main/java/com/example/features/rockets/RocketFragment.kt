package com.example.features.rockets

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
import com.example.features.databinding.FragmentRocketBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RocketFragment : Fragment() {

    private val viewModel: RocketViewModel by viewModel()

    private lateinit var binding: FragmentRocketBinding

    private val adapter by lazy {
        RocketAdapter(
            onClick = {
                Toast.makeText(requireContext(),"Rocket clicked", Toast.LENGTH_SHORT).show()

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

        if(viewModel.rocket.value.isEmpty())
        {
            viewModel.fetchRockets()
        }




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

    private fun setUpObserver(){

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.rocket.collectLatest { response ->
                    adapter.submitList(response)
                }
            }

        }

    }
}