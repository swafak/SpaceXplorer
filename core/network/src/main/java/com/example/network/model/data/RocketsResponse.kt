package com.example.network.model.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class RocketsResponse(
    val height: Height?,
    val diameter: Diameter?,
    val mass: Mass?,
    @Json(name = "flickr_images")
    val flickrImages: List<String>?,
    val name: String?,
    val type: String?,
    val active: Boolean?,
    val stages: Long?,
    val boosters: Long?,
    val costPerLaunch: Long?,
    @Json(name = "success_rate_pct")
    val successRatePct: Long?,
    @Json(name = "first_flight")
    val firstFlight: String?,
    val country: String?,
    val company: String?,
    val wikipedia: String?,
    val description: String?,
    val id: String?
) : Parcelable

@Parcelize
data class Height(
    val meters: Double?,
    val feet: Double?
) : Parcelable

@Parcelize
data class Diameter(
    val meters: Double?,
    val feet: Double?
) : Parcelable

@Parcelize
data class Mass(
    val kg: Long?,
    val lb: Long?
) : Parcelable
