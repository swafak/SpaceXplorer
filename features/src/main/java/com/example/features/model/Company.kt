package com.example.features.model

import android.os.Parcelable
import com.example.network.model.data.CompanyResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CompanyModel(
    val id: String,
    val name: String,
    val summary: String,
    val flickr: String,
    val twitter: String,
    val website: String,
    val address: String,
    val city: String,
    val state: String
): Parcelable

fun CompanyResponse.toModel() = CompanyModel(
    id = id.orEmpty(),
    name = name.orEmpty(),
    summary = summary.orEmpty(),
    flickr = links?.flickr.orEmpty(),
    twitter = links?.twitter.orEmpty(),
    website = links?.website.orEmpty(),
    address = headquarters?.address.orEmpty(),
    city = headquarters?.city.orEmpty(),
    state = headquarters?.state.orEmpty(),
)