package com.example.features.history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.features.model.History

@Composable
fun HistoryScreen(
    history: List<History>
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column {
            LazyColumn {
                items(history) { historyItem ->
                    HistoryCard(
                        history = historyItem
                    )
                }
            }
        }

    }
}