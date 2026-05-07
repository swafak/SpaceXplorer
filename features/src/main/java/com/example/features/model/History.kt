package com.example.features.model

import com.example.network.model.data.HistoryResponseItem

data class History(
    val id: String,
    val title: String,
    val date: String,
    val details: String,
    val articleUrl: String
)

fun HistoryResponseItem.toUiModel() = History(
    id = id,
    title = title.orEmpty(),
    date = eventDateUtc.orEmpty(),
    details = details.orEmpty(),
    articleUrl = links.article.orEmpty()
)