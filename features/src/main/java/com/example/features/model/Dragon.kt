package com.example.features.model

import com.example.data.room.DragonEntity
import com.example.network.model.data.DragonResponse

data class Dragon(
    val id: String,
    val name: String,
    val type: String,
    val description: String,
    val crewCapacity: Long?,
    val firstFlight: String,
    val flickrImages: List<String>
    )

fun DragonResponse.toModel(): Dragon{
    return Dragon(
        id = id,
        name = name.orEmpty(),
        type = type.orEmpty(),
        description = description.orEmpty(),
        crewCapacity = crewCapacity,
        firstFlight = firstFlight.orEmpty(),
        flickrImages = flickrImages.orEmpty()
    )
}

fun DragonEntity.toModel(): Dragon {
    return Dragon(
        id = id,
        name = name.orEmpty(),
        type = type.orEmpty(),
        description = description.orEmpty(),
        crewCapacity = crewCapacity,
        firstFlight = firstFlight.orEmpty(),
        flickrImages = flickrImages.orEmpty()
    )


}
fun Dragon.toEntity(): DragonEntity {
    return DragonEntity(
        id = id,
        name = name,
        type = type,
        crewCapacity = crewCapacity,
        flickrImages = flickrImages,
        description = description,
        firstFlight = firstFlight
    )
}