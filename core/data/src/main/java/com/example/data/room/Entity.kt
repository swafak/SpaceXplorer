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
//    val height: Height?,
//    val diameter: Diameter?,
//    val mass: Mass?,
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
//    val `class`: Int?,
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
//    @Json(name = "mass_kg")
    val massKg: Int?,
//    @Json(name = "mass_lbs")
    val massLbs: Int?,
    val mmsi: Int?,
    val model: String?,
    val name: String?,
    val roles: List<String>?,
    val status: String?,
    val type: String?,
//    @Json(name = "year_built")
    val yearBuilt: Int?
): Parcelable

@Parcelize
@Entity(tableName = "FavDragon")
data class DragonEntity(
//    val heatShield: HeatShield?,
//    val launchPayloadMass: LaunchPayloadMass?,
//    val launchPayloadVol: LaunchPayloadVol?,
//    val returnPayloadMass: ReturnPayloadMass?,
//    val returnPayloadVol: ReturnPayloadVol?,
//    val pressurizedCapsule: PressurizedCapsule?,
//    val trunk: Trunk?,
//    val heightWTrunk: HeightWTrunk?,
//    val diameter: Diameter?,
//    @Json(name = "first_flight")
    val firstFlight: String?,
//    @Json(name ="flickr_images")
    val flickrImages: List<String>?,
    val name: String?,
    val type: String?,
    val active: Boolean?,
//    @Json(name = "crew_capacity")
    val crewCapacity: Long?,
//    val sidewallAngleDeg: Long?,
//    val orbitDurationYr: Long?,
    val dryMassKg: Long?,
    val wikipedia: String?,
    val description: String?,
    @PrimaryKey
    val id: String
) : Parcelable

