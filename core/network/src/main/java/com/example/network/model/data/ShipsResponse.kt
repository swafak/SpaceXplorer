package com.example.network.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

class ShipsResponse : ArrayList<ShipsResponseItem>()

@Parcelize
data class ShipsResponseItem(
    val abs: Int?,
    val active: Boolean?,
    val `class`: Int?,
    val home_port: String?,
    val id: String?,
    val image: String?,
    val imo: Int?,
    val latitude: Double?,
    val launches: List<String>?,
    val legacy_id: String?,
    val link: String?,
    val longitude: Double?,
    val mass_kg: Int?,
    val mass_lbs: Int?,
    val mmsi: Int?,
    val model: String?,
    val name: String?,
    val roles: List<String>?,
    val status: String?,
    val type: String?,
    val year_built: Int?
): Parcelable