package com.example.network.model.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize



class HistoryResponse : ArrayList<HistoryResponseItem>()

@Parcelize
data class HistoryResponseItem(
    val details: String,
    @Json(name="event_date_utc")
    val eventDateUtc: String,
    val id: String,
    val links: Article,
    val title: String
)  : Parcelable

@Parcelize
data class Article(
    val article: String?
) : Parcelable