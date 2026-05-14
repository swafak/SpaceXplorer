package com.example.features.company

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.features.model.CompanyModel
@Composable
fun CompanyScreen(
    company : CompanyModel
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            CompanyCard(
                company = company
            )
        }
    }
}