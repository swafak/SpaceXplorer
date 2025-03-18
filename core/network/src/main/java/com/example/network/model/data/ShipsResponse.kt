package com.example.network.model.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

class ShipsResponse : ArrayList<ShipsResponseItem>()

@Parcelize
data class ShipsResponseItem(
    val abs: Int?,
    val active: Boolean?,
    val `class`: Int?,
    @Json(name = "home_port")
    val homePort: String?,
    val id: String,
    val image: String?,
    val imo: Int?,
    val latitude: Double?,
    val launches: List<String>?,
    @Json(name="legacy_id")
    val legacyId: String?,
    val link: String?,
    val longitude: Double?,
    @Json(name = "mass_kg")
    val massKg: Int?,
    @Json(name = "mass_lbs")
    val massLbs: Int?,
    val mmsi: Int?,
    val model: String?,
    val name: String?,
    val roles: List<String>?,
    val status: String?,
    val type: String?,
    @Json(name = "year_built")
    val yearBuilt: Int?
): Parcelable