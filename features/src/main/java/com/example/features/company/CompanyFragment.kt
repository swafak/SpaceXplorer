package com.example.features.company

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.features.databinding.FragmentCompanyBinding
import com.example.network.model.data.CompanyResponse
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel

class CompanyFragment : Fragment() {

    private val viewModel by lazy {
        requireParentFragment().getViewModel<CompanyViewModel>()
    }

    private var result: CompanyResponse? = null

    private lateinit var binding: FragmentCompanyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompanyBinding.inflate(inflater, container, false)

        viewModel.fetchCompanyInfo()


        setObservers()


        return binding.root
    }

    private fun setObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.companyInfo.collect { result ->
                    result?.let { safeResult ->
                        binding.apply {
                            summary.text = safeResult.summary
                            website.text = safeResult.links.website
                            twitter.text = safeResult.links.twitter
                            flickr.text = safeResult.links.flickr
                            address.text = safeResult.headquarters.address
                            city.text = safeResult.headquarters.city
                            state.text = safeResult.headquarters.state
                        }

                    }
                }
            }
        }
    }
}