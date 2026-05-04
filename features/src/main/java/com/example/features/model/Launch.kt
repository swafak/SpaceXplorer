package com.example.features.model


import android.os.Build
import androidx.annotation.RequiresApi
import com.example.network.model.data.LaunchesResponse

data class Launch(
    val id: String,
    val name: String,
    val dateUtc: String,
    val imageUrl: String?
)

fun LaunchesResponse.toModel(): Launch {
    return Launch(
        id = id.orEmpty(),
        name = name.orEmpty(),
        dateUtc = dateUtc.orEmpty(),
        imageUrl = links?.patch?.small
    )
}
@RequiresApi(Build.VERSION_CODES.O)
fun formatDate(dateUtc: String): String {
    return try {
        val input = java.time.Instant.parse(dateUtc)
        val formatter = java.time.format.DateTimeFormatter
            .ofPattern("MMM dd, yyyy")
            .withZone(java.time.ZoneId.systemDefault())
        formatter.format(input)
    } catch (e: Exception) {
        dateUtc  // if parsing fails, just show the raw string
    }
}