package com.example.network.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize



class HistoryResponse : ArrayList<HistoryResponseItem>()

@Parcelize
data class HistoryResponseItem(
    val details: String,
    val event_date_unix: Int,
    val event_date_utc: String,
    val id: String,
    val links: Article,
    val title: String
)  : Parcelable

@Parcelize
data class Article(
    val article: String?
) : Parcelable