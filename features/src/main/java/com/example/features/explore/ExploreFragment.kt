package com.example.features.explore

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.navigateUp
import com.example.features.R
import com.example.features.databinding.FragmentCompanyBinding
import com.example.features.databinding.FragmentExploreBinding

class ExploreFragment : Fragment() {

    private val viewModel: ExploreViewModel by viewModels()

    private lateinit var binding: FragmentExploreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentExploreBinding.inflate(inflater, container, false)

        binding.apply {

            arrowCompany.setOnClickListener {
                findNavController().navigate(R.id.navigation_company)

            }
            arrowHistory.setOnClickListener {
                findNavController().navigate(R.id.navigation_history)
            }
            arrowLaunches.setOnClickListener{
                findNavController().navigate(R.id.navigation_launches)
            }

//            setNavigationOnClickListener { navigateUp() }
        }

        return  binding.root
    }
}