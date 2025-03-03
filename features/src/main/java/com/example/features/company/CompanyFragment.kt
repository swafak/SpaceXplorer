package com.example.features.company

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.features.databinding.FragmentCompanyBinding
import kotlinx.coroutines.launch

class CompanyFragment : Fragment() {


    private lateinit var binding: FragmentCompanyBinding

    private val args by navArgs<CompanyFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompanyBinding.inflate(inflater, container, false)

        return binding.root
    }

    private fun setObservers() {
        viewLifecycleOwner.lifecycleScope.launch {

            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                binding.apply {

                    summary.text = args.CompanyResponse.summary
                    website.text = args.CompanyResponse.links.website
                    twitter.text = args.CompanyResponse.links.twitter
                    flickr.text = args.CompanyResponse.links.flickr
                    address.text = args.CompanyResponse.headquarters.address
                    city.text = args.CompanyResponse.headquarters.city
                    state.text = args.CompanyResponse.headquarters.state

                }

                }
            }
        }
    }
