package com.example.features.ships

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.common.BottomDialogFragment
import com.example.data.room.FavoriteDB
import com.example.data.room.ShipsEntity
import com.example.features.R
import com.example.features.databinding.FragmentShipsDetailsDialogBinding
import com.example.features.favorites.FavoritesViewModel
import com.example.network.model.data.ShipsResponseItem
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShipsDetailsDialogFragment(private val response: ShipsResponseItem) :
    BottomDialogFragment(R.layout.fragment_ships_details_dialog) {



    private lateinit var binding: FragmentShipsDetailsDialogBinding
    private val viewModel: FavoritesViewModel by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpData()

        val database = FavoriteDB.getDatabase(requireContext())

        binding.favoriteIcon.setOnClickListener {
            toggleFavorite(response)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentShipsDetailsDialogBinding.inflate(inflater, container, false)

        return binding.root

    }

    private fun setUpData() {
        binding.apply {
            "${getString(com.example.resources.R.string.status)} ${response.active.toString()}".also { active.text = it }
            "${getString(com.example.resources.R.string.Mass)} ${response.massKg.toString()}".also { mass.text = it }
            "${getString(com.example.resources.R.string.homePort)} ${response.homePort.toString()}".also { homePort.text = it }
            "${getString(com.example.resources.R.string.year)}${response.yearBuilt.toString()}".also { year.text = it }
            "${getString(com.example.resources.R.string.model)}${response.model}".also { model.text = it }
            Launches.text = response.launches.toString()

            Glide.with(Image.context)
                .load(response.image)
                .placeholder(R.drawable.baseline_rocket_24)
                .into(Image)

        }
    }


    private fun addToFav(item: ShipsResponseItem){
        val ShipsEntity = ShipsEntity(
            id = item.id,
            name = item.name,
            type = item.type,
            active = item.active,
            homePort = item.homePort,
            launches = item.launches,
            latitude = item.latitude,
            longitude = item.longitude,
            link = item.link,
            legacyId = item.legacyId,
            image = item.image,
            mmsi = item.mmsi,
            massKg = item.massKg,
            massLbs = item.massLbs,
            model = item.model,
            imo = item.imo,
            yearBuilt = item.yearBuilt,
            status = item.status,
            roles = item.roles, abs = item.abs

           )
        viewModel.insertShips(ShipsEntity)

    }
    private fun removeFromFavorite(item: ShipsResponseItem){
        item.id.let{
            viewModel.deleteShip(it)
        }
    }
    private fun toggleFavorite(item: ShipsResponseItem) {
        item.id.let {
            viewModel.isFavoriteShip(it).observe(viewLifecycleOwner, Observer { isFavorite ->
                if (isFavorite) {
                    Toast.makeText(context, "Removed from favorite", Toast.LENGTH_SHORT).show()
                    removeFromFavorite(item)
                    updateFavoriteIcon(false)
                } else {
                    addToFav(item)
                    Toast.makeText(context, "Added to favorite", Toast.LENGTH_SHORT).show()
                    updateFavoriteIcon(true)
                }
            })
        }
    }

    private fun updateFavoriteIcon(isFavorite: Boolean) {
        val color = if (isFavorite) com.example.resources.R.color.blue else com.example.resources.R.color.white
        binding.favoriteIcon.setColorFilter(ContextCompat.getColor(requireContext(), color))
    }

}