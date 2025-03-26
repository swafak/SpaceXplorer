package com.example.features.model

import com.example.data.room.RocketEntity
import com.example.network.model.data.Height
import com.example.network.model.data.Mass
import com.example.network.model.data.RocketsResponse

data class Rocket(
    val id: String,
    val flickrImages: List<String>?,
    val name: String?,
    val active: Boolean?,
    val successRatePct: Long?,
    val firstFlight: String?,
    val wikipedia: String?,
    val description: String?,
    val height: Height?,
    val mass: Mass?,
    val type: String?,
    val stages: Long?,
    val costPerLaunch: Long?,
    val country: String?,
    val company: String?
)

fun RocketEntity.toModel() = Rocket(
    id = id,
    flickrImages = flickrImages,
    name = name,
    active = active,
    firstFlight = firstFlight,
    wikipedia = wikipedia,
    description = description,
    successRatePct = successRatePct,
    height = null,
    mass = null,
    type = null,
    stages = null,
    costPerLaunch = null,
    country = null,
    company = null,
)

fun RocketsResponse.toModel() = Rocket(
    id = id,
    flickrImages = flickrImages,
    name = name,
    active = active,
    firstFlight = firstFlight,
    wikipedia = wikipedia,
    description = description,
    successRatePct = successRatePct,
    height = height,
    mass = mass,
    type = type,
    stages = stages,
    costPerLaunch = costPerLaunch,
    country = country,
    company = company
)

fun List<RocketsResponse>.toRockets() : List<Rocket> = this.map { it.toModel() }
fun List<RocketEntity>.toRocketEntity(): List<Rocket> = this.map{
    it.toModel()
}


fun Rocket.toRocketResponse() = RocketsResponse(
    id = id,
    flickrImages = flickrImages,
    name = name,
    active = active,
    firstFlight = firstFlight,
    wikipedia = wikipedia,
    description = description,
    successRatePct = successRatePct,
    height = height,
    mass = mass,
    type = type,
    stages = stages,
    costPerLaunch = costPerLaunch,
    country = country,
    company = company,
    diameter = null,
    boosters = null,
)
