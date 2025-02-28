package com.example.network.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CompanyResponse(
    val ceo: String,
    val coo: String,
    val cto: String,
    val cto_propulsion: String,
    val employees: Int,
    val founded: Int,
    val founder: String,
    val headquarters: Headquarters,
    val id: String,
    val launch_sites: Int,
    val links: Links,
    val name: String,
    val summary: String,
    val test_sites: Int,
    val valuation: Long,
    val vehicles: Int
) : Parcelable

@Parcelize
data class Links(
    val elon_twitter: String,
    val flickr: String,
    val twitter: String,
    val website: String
): Parcelable

@Parcelize
data class Headquarters(
    val address: String,
    val city: String,
    val state: String
): Parcelable