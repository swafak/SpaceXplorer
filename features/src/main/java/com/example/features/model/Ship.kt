package com.example.features.model

import com.example.data.room.ShipsEntity
import com.example.network.model.data.ShipsResponseItem

data class Ship(
    val active: Boolean?,
    val homePort: String?,
    val id: String,
    val image: String?,
    val launches: List<String>?,
    val massKg: Int?,
    val model: String?,
    val name: String?,
    val status: String?,
    val yearBuilt: Int?,
    val type: String?
)
fun Ship.toShipsResponse()= ShipsResponseItem(
    yearBuilt = yearBuilt,
    active = active,
    homePort = homePort,
    id = id,
    image = image,
    massKg = massKg,
    model = model,
    name = name,
    status = status,
    launches = launches,
    abs =null,
    `class` = null,
    imo = null,
    latitude = null,
    legacyId = null,
    link = null,
    longitude =null,
    massLbs = null,
    mmsi = null,
    roles = null,
    type = type
)

fun ShipsResponseItem.toModel() = Ship(
    yearBuilt = yearBuilt,
    active = active,
    homePort = homePort,
    id = id,
    image = image,
    massKg = massKg,
    model = model,
    name = name,
    status = status,
    launches = launches,
    type = type
)

fun ShipsEntity.toModel()= Ship(
    yearBuilt = yearBuilt,
    active = active,
    homePort = homePort,
    id = id,
    image = image,
    massKg = massKg,
    model = model,
    name = name,
    status = status,
    launches = launches,
    type = type
)
fun List<ShipsResponseItem>.toShips() : List<Ship> = this.map { it.toModel() }
fun List<ShipsEntity>.toShipsEntity() : List<Ship> = this.map { it.toModel() }
