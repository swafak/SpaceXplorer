package com.example.features.rockets

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.common.BottomDialogFragment
import com.example.data.room.FavoriteDB
import com.example.data.room.RocketEntity
import com.example.features.R
import com.example.features.databinding.FragmentRocketDetailDialogBinding
import com.example.features.favorites.FavoritesViewModel
import com.example.network.model.data.RocketsResponse
import org.koin.androidx.viewmodel.ext.android.viewModel
class RocketDetailDialogFragment(private val response: RocketsResponse) : BottomDialogFragment(
    R.layout.fragment_rocket_detail_dialog
) {

    private lateinit var binding: FragmentRocketDetailDialogBinding
    private val viewModel: FavoritesViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = FavoriteDB.getDatabase(requireContext())

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpdata()

        binding.favoriteIcon.setOnClickListener{
            toggleFavorite(response)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRocketDetailDialogBinding.inflate(inflater, container, false)

        return binding.root
    }
     private fun setUpdata() {
        binding.apply {
            description.text = response.description
            website.text = response.wikipedia
            height.text = response.height?.meters.toString()
            mass.text = response.mass?.kg.toString()
            firstFlight.text = response.firstFlight
            successRatePct.text = response.successRatePct.toString()
            active.text = response.active.toString()

            Log.d("IMAGE", "SEE: ${response.flickrImages?.first()}")
//
//            viewLifecycleOwner.lifecycleScope.launch{
//                Glide.with(dialog?.context ?: requireContext())
//                    .load(response.flickrImages?.first())
//                    .fitCenter()
//                    .placeholder(R.drawable.baseline_rocket_24)
////                .dontAnimate()
////                .error(R.drawable.baseline_rocket_24)
//                    .into(Image)

//            }

//            Image.load(response.flickrImages?.first()) {
//                crossfade(true)
//                placeholder(R.drawable.baseline_rocket_24)
//                error(R.drawable.baseline_rocket_24)
//            }


        }
    }

    private fun addToFav(item: RocketsResponse){
        val rocketEntity = RocketEntity(
                id = item.id,
                name = item.name,
                type = item.type,
                country = item.country,
                costPerLaunch = item.costPerLaunch,
                description = item.description,
                wikipedia = item.wikipedia,
                stages = item.stages,
                successRatePct = item.successRatePct,
                firstFlight = item.firstFlight,
                flickrImages = item.flickrImages,
                active = item.active,
                boosters = item.boosters,
                company = item.company)
        viewModel.insertRocket(rocketEntity)

        }
    private fun removeFromFavorite(item: RocketsResponse){
        item.id?.let{
            viewModel.deleteRocket(it)
        }
    }
    private fun toggleFavorite(item: RocketsResponse) {
        item.id?.let {
            viewModel.isFavRocket(it).observe(viewLifecycleOwner, Observer { isFavorite ->
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

