package com.example.amphibians.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen() {

}

@Composable
fun PhotoGrid(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(8.dp)
    ){
        item {
            AmphibianPhotoCard()
        }
    }
}

@Composable
fun AmphibianPhotoCard(modifier: Modifier = Modifier) {
    Card(modifier = modifier) {

    }
}