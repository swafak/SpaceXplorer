package com.example.features.model

import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import com.example.network.model.data.HistoryResponseItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class History(
    val id: String,
    val title: String,
    val date: String,
    val details: String,
    val articleUrl: String
): Parcelable

fun HistoryResponseItem.toUiModel() = History(
    id = id,
    title = title.orEmpty(),
    date = eventDateUtc.orEmpty(),
    details = details.orEmpty(),
    articleUrl = links.article.orEmpty()
)