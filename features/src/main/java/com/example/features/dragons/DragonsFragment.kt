package com.example.features.dragons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionManager
import com.example.data.room.DragonEntity
import com.example.data.room.FavoriteDB
import com.example.features.databinding.FragmentDragonsBinding
import com.example.features.favorites.FavoritesViewModel
import com.example.network.model.data.DragonResponse
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DragonsFragment : Fragment() {


    private val viewModel: DragonsViewModel by viewModel()
    private val favoritesViewModel : FavoritesViewModel by viewModel()
    private lateinit var binding: FragmentDragonsBinding

    private val adapter by lazy {
        DragonAdapter(
            onFavoriteClick = { dragon -> toggleFavorite(dragon) },
            isFavorite = { dragonId -> favoritesViewModel.isFavDragon(dragonId) } // ✅ Now returns a Boolean
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        DB initialization
        val database = FavoriteDB.getDatabase(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUprecycler()
        viewModel.fetchDragon()
        setUpObserver()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDragonsBinding.inflate(inflater, container, false)

        return binding.root
    }

    private fun setUprecycler() {

        binding.Recycler.adapter = adapter
        binding.Recycler.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun renderLoading(isLoading: Boolean) {
        binding.apply {
            TransitionManager.beginDelayedTransition(binding.root)
            loading.isVisible = isLoading
            Recycler.isGone = isLoading
        }
    }

    private fun setUpObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.uiState.collectLatest { uistate ->

                    renderLoading(uistate.isLoading)
                    uistate.dragonInfo.let {
                        adapter.submitList(uistate.dragonInfo)
                    }
                }

            }
        }

    }
    private fun toggleFavorite(item: DragonResponse) {
        val entity = DragonEntity(
            id = item.id,
            name = item.name,
            type = item.type,
            active = item.active,
            crewCapacity = item.crewCapacity,
            flickrImages = item.flickrImages,
            description = item.description,
            wikipedia = item.wikipedia,
            dryMassKg = item.dryMassKg,
            firstFlight = item.firstFlight
        )

        if (favoritesViewModel.isFavDragon(item.id)) {
            favoritesViewModel.deleteDragon(item.id)
        } else {
            favoritesViewModel.insertDragon(entity)
        }
    }
}
