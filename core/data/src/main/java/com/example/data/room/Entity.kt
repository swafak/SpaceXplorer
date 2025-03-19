package com.example.data.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "FavRocket")
data class RocketEntity(
    @PrimaryKey
    val id: String,
    val flickrImages: List<String>?,
    val name: String?,
    val type: String?,
    val active: Boolean?,
    val stages: Long?,
    val boosters: Long?,
    val costPerLaunch: Long?,
    val successRatePct: Long?,
    val firstFlight: String?,
    val country: String?,
    val company: String?,
    val wikipedia: String?,
    val description: String?,
): Parcelable

@Parcelize
@Entity(tableName = "FavShips")
data class ShipsEntity(
    val abs: Int?,
    val active: Boolean?,
    val homePort: String?,
    @PrimaryKey
    val id: String,
    val image: String?,
    val imo: Int?,
    val latitude: Double?,
    val launches: List<String>?,
    val legacyId: String?,
    val link: String?,
    val longitude: Double?,
    val massKg: Int?,
    val massLbs: Int?,
    val mmsi: Int?,
    val model: String?,
    val name: String?,
    val roles: List<String>?,
    val status: String?,
    val type: String?,
    val yearBuilt: Int?
): Parcelable

@Parcelize
@Entity(tableName = "FavDragon")
data class DragonEntity(
    val firstFlight: String?,
    val flickrImages: List<String>?,
    val name: String?,
    val type: String?,
    val active: Boolean?,
    val crewCapacity: Long?,
    val dryMassKg: Long?,
    val wikipedia: String?,
    val description: String?,
    @PrimaryKey
    val id: String
) : Parcelable

