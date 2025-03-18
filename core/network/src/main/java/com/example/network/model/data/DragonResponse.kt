package com.example.network.model.data


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class DragonResponse(
    val heatShield: HeatShield?,
    val launchPayloadMass: LaunchPayloadMass?,
    val launchPayloadVol: LaunchPayloadVol?,
    val returnPayloadMass: ReturnPayloadMass?,
    val returnPayloadVol: ReturnPayloadVol?,
    val pressurizedCapsule: PressurizedCapsule?,
    val trunk: Trunk?,
    val heightWTrunk: HeightWTrunk?,
    val diameter: Diameter?,
    @Json(name = "first_flight")
    val firstFlight: String?,
    @Json(name ="flickr_images")
    val flickrImages: List<String>?,
    val name: String?,
    val type: String?,
    val active: Boolean?,
    @Json(name = "crew_capacity")
    val crewCapacity: Long?,
    val sidewallAngleDeg: Long?,
    val orbitDurationYr: Long?,
    val dryMassKg: Long?,
    val dryMassLb: Long?,
    val wikipedia: String?,
    val description: String?,
    val id: String
) : Parcelable

@Parcelize
data class HeatShield(
    val material: String?,
    val sizeMeters: Double?,
    val tempDegrees: Long?,
    val devPartner: String?
) : Parcelable

@Parcelize
data class LaunchPayloadMass(
    val kg: Long?,
    val lb: Long?
) : Parcelable

@Parcelize
data class LaunchPayloadVol(
    val cubicMeters: Long?,
    val cubicFeet: Long?
) : Parcelable

@Parcelize
data class ReturnPayloadMass(
    val kg: Long?,
    val lb: Long?
) : Parcelable

@Parcelize
data class ReturnPayloadVol(
    val cubicMeters: Long?,
    val cubicFeet: Long?
) : Parcelable

@Parcelize
data class PressurizedCapsule(
    val payloadVolume: PayloadVolume?
) : Parcelable

@Parcelize
data class PayloadVolume(
    val cubicMeters: Long?,
    val cubicFeet: Long?
) : Parcelable

@Parcelize
data class Trunk(
    val trunkVolume: TrunkVolume?,
    val cargo: Cargo?
) : Parcelable

@Parcelize
data class TrunkVolume(
    val cubicMeters: Long?,
    val cubicFeet: Long?
) : Parcelable

@Parcelize
data class Cargo(
    val solarArray: Long?,
    val unpressurizedCargo: Boolean?
) : Parcelable

@Parcelize
data class HeightWTrunk(
    val meters: Double?,
    val feet: Double?
) : Parcelable


