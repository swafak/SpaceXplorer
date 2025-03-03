package com.example.network.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LaunchesResponse(
    val fairings: Fairings?,
    val links: Links_launches?,
    val staticFireDateUtc: String?,
    val staticFireDateUnix: Long?,
    val net: Boolean?,
    val window: Long?,
    val rocket: String?,
    val success: Boolean?,
    val failures: List<Failure>?,
    val details: String?,
    val crew: List<Crew>?,
    val ships: List<String>?,
    val capsules: List<String>?,
    val payloads: List<String>?,
    val launchpad: String?,
    val flightNumber: Long?,
    val name: String?,
    val dateUtc: String?,
    val dateUnix: Long?,
    val dateLocal: String?,
    val datePrecision: String?,
    val upcoming: Boolean?,
    val cores: List<Core>?,
    val autoUpdate: Boolean?,
    val tbd: Boolean?,
    val launchLibraryId: String?,
    val id: String?
) : Parcelable

@Parcelize
data class Fairings(
    val reused: Boolean?,
    val recoveryAttempt: Boolean?,
    val recovered: Boolean?,
    val ships: List<String>?
) : Parcelable

@Parcelize
data class Links_launches(
    val patch: Patch?,
    val reddit: Reddit?,
    val flickr: Flickr?,
    val presskit: String?,
    val webcast: String?,
    val youtubeId: String?,
    val article: String?,
    val wikipedia: String?
) : Parcelable

@Parcelize
data class Patch(
    val small: String?,
    val large: String?
) : Parcelable

@Parcelize
data class Reddit(
    val campaign: String?,
    val launch: String?,
    val media: String?,
    val recovery: String?
) : Parcelable

@Parcelize
data class Flickr(
    val original: List<String>?
) : Parcelable

@Parcelize
data class Failure(
    val time: Long?,
    val altitude: Long?,
    val reason: String?
) : Parcelable

@Parcelize
data class Crew(
    val crew: String?,
    val role: String?
) : Parcelable

@Parcelize
data class Core(
    val core: String?,
    val flight: Long?,
    val gridfins: Boolean?,
    val legs: Boolean?,
    val reused: Boolean?,
    val landingAttempt: Boolean?,
    val landingSuccess: Boolean?,
    val landingType: String?,
    val landpad: String?
) : Parcelable
